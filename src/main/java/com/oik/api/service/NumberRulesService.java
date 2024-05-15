package com.oik.api.service;

import com.github.yulichang.base.MPJBaseService;
import com.oik.api.entity.NumberRules;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2024-01-18
 */
public interface NumberRulesService extends MPJBaseService<NumberRules> {

    NumberRules initRule(String code);

    String generateId(String code);
}
