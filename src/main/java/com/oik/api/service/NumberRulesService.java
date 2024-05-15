package com.oik.api.service;

import com.github.yulichang.base.MPJBaseMapper;
import com.oik.api.entity.NumberRules;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2024-01-18
 */
public interface NumberRulesService extends MPJBaseMapper<NumberRules> {

    NumberRules initRule(String code);

    String generateId(String code);
}
