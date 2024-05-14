package com.oik.api.config.mybatis;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.oik.api.config.note.IdRule;
import com.oik.api.service.NumberRulesService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Resource
    @Lazy
    private NumberRulesService numberRulesService;

    @Override
    public boolean assignId(Object idValue) {
        return IdentifierGenerator.super.assignId(idValue);
    }

    @Override
    public Number nextId(Object entity) {
        return IdUtil.getSnowflake(1, 1).nextId();
    }

    @Override
    public String nextUUID(Object entity) {
        Class<?> aClass = entity.getClass();
        IdRule annotation = aClass.getAnnotation(IdRule.class);
        if (annotation != null){
            String code = annotation.code();
            return numberRulesService.generateId(code);
        }
        return IdentifierGenerator.super.nextUUID(entity);
    }
}
