package com.oik.api.service.impl;

import com.oik.api.entity.Dict;
import com.oik.api.mapper.DictMapper;
import com.oik.api.service.DictService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Service
public class DictServiceImpl extends MPJBaseServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> dictAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public void saveOne(Dict dict) {
        save(dict);
    }

    @Override
    public Map<String, List<Dict>> dictAllMap() {
        List<Dict> list = dictAll();
        List<Dict> collect = list
                .stream()
                .filter(e -> StringUtils.isNotEmpty(e.getParent())).toList();
        return collect
                .stream()
                .collect(Collectors.groupingBy(Dict::getParent,
                        Collectors.mapping(Function.identity(),
                                Collectors.toList())));
    }
}
