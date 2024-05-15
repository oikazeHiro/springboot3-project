package com.oik.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.Dict;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
public interface DictService extends MPJBaseService<Dict> {
    List<Dict> dictAll();

    void saveOne(Dict dict);

    Map<String,List<Dict>> dictAllMap();
}
