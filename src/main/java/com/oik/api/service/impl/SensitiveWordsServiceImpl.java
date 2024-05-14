package com.oik.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.SensitiveWords;
import com.oik.api.mapper.SensitiveWordsMapper;
import com.oik.api.service.SensitiveWordsService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.api.utils.pages.PagePlus;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Page<SensitiveWords> find(PagePlus<SensitiveWords> pagePlus) {
        SensitiveWords obj = pagePlus.getObj();// 查询条件
        LambdaQueryWrapper<SensitiveWords> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(obj.getWords()) // 第一个参数为 boolean
                // 相当于 jpql 写的if (HdUtils.strNotNull(hdQuery.getStr("zeroPrice")))
                ,SensitiveWords::getWords, // 获取数据库表字段名
                obj.getWords() // 参数
        );
        return baseMapper.selectPage(pagePlus,wrapper);
    }


}
