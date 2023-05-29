package com.oik.api.service;

import com.oik.api.entity.SensitiveWords;
import com.github.yulichang.base.service.MPJJoinService;

import java.util.List;

/**
 * <p>
 * 敏感词表 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-29
 */
public interface SensitiveWordsService extends MPJJoinService<SensitiveWords> {
    List<String> findAll();
    SensitiveWords saveOne(SensitiveWords words);
    SensitiveWords updateOne(SensitiveWords words);
    boolean deleteOne(String id);
}