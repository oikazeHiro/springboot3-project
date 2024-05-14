package com.oik.api.service;

import com.oik.api.entity.NumberRules;
import com.github.yulichang.base.service.MPJJoinService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2024-01-18
 */
public interface NumberRulesService extends MPJJoinService<NumberRules> {

    NumberRules initRule(String code);

    String generateId(String code);
}
