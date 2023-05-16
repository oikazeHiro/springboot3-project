package com.oik.api.service.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.api.entity.Menu;
import com.oik.api.entity.Role;
import com.oik.api.entity.RoleMenu;
import com.oik.api.entity.UserRole;
import com.oik.api.mapper.MenuMapper;
import com.oik.api.service.MenuService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getParams(String userId) {
        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Menu.class)
                .leftJoin(RoleMenu.class,RoleMenu::getMenuId,Menu::getId)
                .leftJoin(Role.class,Role::getId,RoleMenu::getRoleId)
                .leftJoin(UserRole.class,UserRole::getRoleId,Role::getId)
                .eq(UserRole::getUserId,userId);
        List<Menu> menus = selectJoinList(Menu.class, wrapper);
        if (menus == null){
            menus = new ArrayList<>();
        }
        return menus.stream().map(Menu::getPerms).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }
}
