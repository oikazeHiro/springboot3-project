package com.oik.api.config.mybatis;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/16 17:33
 */
public class CustomIdGenerator implements IdentifierGenerator {
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
        return IdentifierGenerator.super.nextUUID(entity);
    }
}
