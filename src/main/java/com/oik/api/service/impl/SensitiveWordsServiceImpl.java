package com.oik.api.service.impl;

import com.oik.api.entity.SensitiveWords;
import com.oik.api.mapper.SensitiveWordsMapper;
import com.oik.api.service.SensitiveWordsService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 敏感词表 服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-29
 */
@Service
public class SensitiveWordsServiceImpl extends MPJBaseServiceImpl<SensitiveWordsMapper, SensitiveWords> implements SensitiveWordsService {

    @Override
    public List<String> findAll() {
        List<SensitiveWords> sensitiveWords = baseMapper.selectList(null);
        return sensitiveWords.stream().map(SensitiveWords::getWords).toList();
    }

    @Override
    public SensitiveWords saveOne(SensitiveWords words) {
        save(words);
        return words;
    }

    @Override
    public SensitiveWords updateOne(SensitiveWords words) {
        updateById(words);
        return words;
    }

    @Override
    public boolean deleteOne(String id) {
        return removeById(id);
    }
}
