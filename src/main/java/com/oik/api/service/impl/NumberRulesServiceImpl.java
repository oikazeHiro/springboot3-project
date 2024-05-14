package com.oik.api.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.api.entity.NumberRules;
import com.oik.api.mapper.NumberRulesMapper;
import com.oik.api.service.NumberRulesService;
import com.oik.api.utils.exception.ServerException;
import com.oik.api.utils.http.ErrorCode;
import com.oik.api.utils.redis.CacheClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.oik.api.utils.redis.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2024-01-18
 */
@Service
@Slf4j
public class NumberRulesServiceImpl extends MPJBaseServiceImpl<NumberRulesMapper, NumberRules> implements NumberRulesService {

    @Resource
    @Lazy
    private NumberRulesServiceImpl numberRulesService;

    @Resource
    private CacheClient cacheClient;
    @Override
    @Transactional
    public NumberRules initRule(String code) {
        NumberRules numberRules = new NumberRules();
        numberRules.setCode(code)
                .setRegular("yyyy-MM-dd")
                .setNum(0)
                .setConnectors("-")
                .setId(getRoleId(numberRules))
        ;
        return numberRules;
    }

    @Override
    @Transactional
    public String generateId(String code) {
        String key = CACHE_RULE+code;
        String lock = CACHE_RULE_LOCK+code;
        try {
            if (cacheClient.tryLock(lock)){
                String value = cacheClient.getValue(key);
                NumberRules numberRules = null;
                if (StringUtils.isNotEmpty(value)){
                    numberRules = JSON.parseObject(value, NumberRules.class);
                }else {
                    LambdaQueryWrapper<NumberRules> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(NumberRules::getCode,code);
                    numberRules = baseMapper.selectOne(wrapper);
                    if (numberRules == null) {
                        numberRules = numberRulesService.initRule(code);
                    }
                }
                String id = getRoleId(numberRules);
                saveOrUpdate(numberRules);
                cacheClient.set(key,JSON.toJSONString(numberRules),CACHE_MENU_TIME, TimeUnit.MINUTES);
                return id;
            }else {
                Thread.sleep(200);
                numberRulesService.generateId(code);
            }
        }catch (InterruptedException e){
            log.error("生成主键失败: {}",e.getMessage());
            Thread.currentThread().interrupt();
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }finally {
            cacheClient.unLock(lock);
        }
        return null;
    }

    private String getRoleId(NumberRules numberRules) {
        SimpleDateFormat sb = new SimpleDateFormat(numberRules.getRegular());
        Date date = new Date();
        String format = sb.format(date);
        if (!StringUtils.equals(format,numberRules.getDateStr())){
            numberRules.setDateStr(format).setNum(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        numberRules.setNum(numberRules.getNum()+1);
        stringBuilder.append(numberRules.getCode())
                .append(numberRules.getConnectors())
                .append(numberRules.getDateStr())
                .append(numberRules.getConnectors())
                .append(numberRules.getNum());
        return stringBuilder.toString();
    }

}
