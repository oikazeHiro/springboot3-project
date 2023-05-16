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
import java.util.ArrayList;
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

    //线程池
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(20);

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

    public <T,R> List<T> getListValue(String prefix,String key,R r,Class<T> type,Function<R,List<T>> dbFallback, Long time, TimeUnit timeUnit){
        String value = getValue(prefix, key);
        if (StringUtils.isNotEmpty(value)){
            return JSON.parseArray(value, type);
        }else{
            try {
                if(tryLock(CACHE_LOCK + key)){
                    List<T>  t = dbFallback.apply(r);
                    if (t == null){
                        t = new ArrayList<>();
                    }
                    set(prefix+key,JSON.toJSONString(t));
                    return t;
                }else{
                    Thread.sleep(200);
                    return getListValue(prefix,key,r,type,dbFallback,time,timeUnit);
                }
            }catch (Exception e){
                log.error(e.getMessage());
            }finally {
                unLock(CACHE_LOCK+key);
            }
        }
        return null;
    }

    public <T,R> List<T> getListValue(String prefix,String key,R r,Class<T> type,Function<R,List<T>> dbFallback){
        String value = getValue(prefix, key);
        if (StringUtils.isNotEmpty(value)){
            return JSON.parseArray(value, type);
        }else{
            try {
                if(tryLock(CACHE_LOCK + key)){
                    List<T>  t = dbFallback.apply(r);
                    if (t == null){
                        t = new ArrayList<>();
                    }
                    set(prefix+key,JSON.toJSONString(t));
                    return t;
                }else{
                    Thread.sleep(200);
                    return getListValue(prefix,key,r,type,dbFallback);
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
