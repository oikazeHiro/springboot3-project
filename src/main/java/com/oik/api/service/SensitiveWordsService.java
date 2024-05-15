package com.oik.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.SensitiveWords;
import com.github.yulichang.base.MPJBaseService;
import com.oik.api.utils.pages.PagePlus;

import java.util.List;

/**
 * <p>
 * 敏感词表 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-29
 */
public interface SensitiveWordsService extends MPJBaseService<SensitiveWords> {
    List<String> findAll();
    SensitiveWords saveOne(SensitiveWords words);
    SensitiveWords updateOne(SensitiveWords words);
    boolean deleteOne(String id);

    Page<SensitiveWords> find(PagePlus<SensitiveWords> pagePlus);
}
