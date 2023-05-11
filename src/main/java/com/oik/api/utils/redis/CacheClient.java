package com.oik.api.utils.redis;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.oik.api.entity.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.oik.api.utils.redis.RedisConstants.CACHE_LOCK;


@Component
@Slf4j
public class CacheClient {
    @Resource
    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 向redis插入带有过期时间的数据
     * @param key key
     * @param value 值
     * @param time 时间
     * @param timeUnit 时间单位
     */
    public void set(String key, Object value, Long time, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),time,timeUnit);
    }
    public void set(String key, Object value){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value));
    }

    /**
     * 向redis插入带有逻辑过期的数据，用于解决缓存穿透
     * @param key key
     * @param value 值
     * @param time 时间
     * @param timeUnit 时间单位
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit timeUnit){
        RedisData redisData = new RedisData(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(time)), value);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 解决缓存穿透
     * @param keyPrefix key前缀
     * @param id id
     * @param type class
     * @param dbFallback 函数试方法
     * @param time time
     * @param timeUnit 时间单位
     * @return 结果
     * @param <R> 返回结果
     * @param <ID> 参数
     */
//    public <R, ID> R queryWithPassThrough(
//            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit timeUnit
//    ) throws InterruptedException {
//        String key = keyPrefix + id;
//        // redis 查取缓存
//        String json = stringRedisTemplate.opsForValue().get(key);
//        log.info(json);
//        // 判断缓存是否命中
//        if (StrUtil.isNotBlank(json)) {
//            return JSON.parseObject(json, type);
////            return JSONUtil.toBean(json, type);
//        }
//        //判断是否是空值
//        if (json != null) {
//            return null;
//        }
//        String lockKey = LOCK_SHOP_KEY + id;
//        boolean lock = tryLock(lockKey);
//        if (lock) {
//            EXECUTOR_SERVICE.submit(() -> {
//                try {
//                    R r = dbFallback.apply(id);
//                    if (r == null) {
//                        stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
//                    }
//                    this.set(key, r, time, timeUnit);
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                } finally {
//                    unLock(lockKey);
//                }
//            });
//        } else {
//            Thread.sleep(100L);
//        }
//        return queryWithPassThrough(keyPrefix, id, type, dbFallback, time, timeUnit);
//    }


//    public <R, ID> List<R> queryWithPassThroughList(
//            String keyPrefix, ID id, Class<R> type, Function<ID, List<R>> dbFallback, Long time, TimeUnit timeUnit
//    ) throws InterruptedException {
//        String key = keyPrefix + id;
//        // redis 查取缓存
//        String json = stringRedisTemplate.opsForValue().get(key);
//        log.info(json);
//        // 判断缓存是否命中
//        if (StrUtil.isNotBlank(json)) {
//            return JSON.parseArray(json, type);
////            return JSONUtil.toBean(json, type);
//        }
//        //判断是否是空值
//        if (json != null) {
//            return null;
//        }
//        String lockKey = LOCK_SHOP_KEY + id;
//        boolean lock = tryLock(lockKey);
//        if (lock) {
//            EXECUTOR_SERVICE.submit(() -> {
//                try {
//                    List<R> r = dbFallback.apply(id);
//                    if (r == null) {
//                        stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
//                    }
//                    this.set(key, r, time, timeUnit);
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                } finally {
//                    unLock(lockKey);
//                }
//            });
//        } else {
//            Thread.sleep(100L);
//        }
//        return queryWithPassThroughList(keyPrefix, id, type, dbFallback, time, timeUnit);
//    }

    //线程池
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(20);

    /**
     * 解决热点key缓存击穿问题
     * @param keyPrefix key前缀
     * @param id id
     * @param type class
     * @param dbFallback 函数试方法
     * @param time time
     * @param timeUnit 时间单位
     * @return 结果
     * @param <R> 返回结果
     * @param <ID> 参数
     */
//    public <R,ID> R queryWithLogicalExpire(
//            String keyPrefix,ID id,Class<R> type, Function<ID,R> dbFallback, Long time, TimeUnit timeUnit
//    ) {
//        String key = keyPrefix + id;
//        // redis 查取缓存
//        String shopJson = stringRedisTemplate.opsForValue().get(key);
//
//        if (StrUtil.isBlank(shopJson)) {
//            return null;
//        }
//        RedisData redisData = JSONUtil.toBean(shopJson, RedisData.class);
//        JSONObject jsonObject = (JSONObject) redisData.getData();
//        R r = JSONUtil.toBean(jsonObject, type);
//        LocalDateTime expireTime = redisData.getExpireTime();
//        if (expireTime.isAfter(LocalDateTime.now())) {
//            return r;
//        }
//        String lockKey = LOCK_SHOP_KEY + id;
//        boolean lock = tryLock(lockKey);
//        if (lock) {
//            EXECUTOR_SERVICE.submit(()->{
//                try {
//                    R r1 = dbFallback.apply(id);
//                    this.setWithLogicalExpire(key,r1,time,timeUnit);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    unLock(lockKey);
//                }
//            });
//
//        }
//        return r;
//    }

    /**
     * 获取互斥锁
     *
     */
    public boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10L, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 释放锁
     *
     * @param key key
     */
    public void unLock(String key) {
        stringRedisTemplate.delete(key);
    }

    public String getValue(String keyPrefix,String key){
         return stringRedisTemplate.opsForValue().get(keyPrefix + key);
    }

    /**
     * 如果缓存没有数据，则去查数据库
     * @param cacheSelector
     * @param databaseSelector
     * @return
     * @param <T>
     */
//    public static <T> T selectCacheByTemplate(CacheSelector<T> cacheSelector, Supplier<T> databaseSelector) {
//        try {
//            log.debug("query data from redis ······");
//            // 先查 Redis缓存
//            T t = cacheSelector.select();
//            if (t == null) {
//                // 没有记录再查询数据库
//                return databaseSelector.get();
//            } else {
//                return t;
//            }
//        } catch (Exception e) {
//            // 缓存查询出错，则去数据库查询
//            log.error("redis error：", e);
//            log.debug("query data from database ······");
//            return databaseSelector.get();
//        }
//    }

    /**
     *根据key删除redis数据
     * @param key
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 根据key前缀批量删除数据
     * @param key
     * @return 删除总条数
     */
    public Long deletes(String key) {
        Set<String> keys = stringRedisTemplate.keys(key + "*");
        assert keys != null;
        return stringRedisTemplate.delete(keys);
    }

    public <T,R> T getValue(String prefix,String key,R r, Class<T> type,Function<R,T> dbFallback, Long time, TimeUnit timeUnit){
        String value = getValue(prefix, key);
        if (StringUtils.isNotEmpty(value)){
            return JSON.parseObject(value, type);
        }else{
            try {
                if(tryLock(CACHE_LOCK + key)){
                    T t = dbFallback.apply(r);
                    set(prefix+key,JSON.toJSONString(t),time,timeUnit);
                    return t;
                }else{
                    Thread.sleep(200);
                   return getValue(prefix,key,r,type,dbFallback,time,timeUnit);
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }finally {
                unLock(CACHE_LOCK+key);
            }
        }
        return null;
    }

    public <T,R> T getValue(String prefix,String key,R r,Class<T> type,Function<R,T> dbFallback){
        String value = getValue(prefix, key);
        if (StringUtils.isNotEmpty(value)){
            return JSON.parseObject(value, type);
        }else{
            try {
                if(tryLock(CACHE_LOCK + key)){
                    T t = dbFallback.apply(r);
                    set(prefix+key,JSON.toJSONString(t));
                    return t;
                }else{
                    Thread.sleep(200);
                    return getValue(prefix,key,r,type,dbFallback);
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }finally {
                unLock(CACHE_LOCK+key);
            }
        }
        return null;
    }

}
