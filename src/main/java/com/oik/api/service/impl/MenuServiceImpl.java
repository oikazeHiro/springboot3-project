package com.oik.api.service.impl;

import com.oik.api.entity.Menu;
import com.oik.api.mapper.MenuMapper;
import com.oik.api.service.MenuService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Service
public class MenuServiceImpl extends MPJBaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public Set<String> getParams(String userId) {
        return null;
    }
}
