package com.oik.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.api.entity.Role;
import com.oik.api.entity.UserRole;
import com.oik.api.mapper.RoleMapper;
import com.oik.api.service.RoleService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.api.utils.pages.PagePlus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Service
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> getByUserId(String userId) {
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Role.class)
                .leftJoin(UserRole.class,UserRole::getRoleId,Role::getId)
                .eq(UserRole::getUserId,userId);
        return selectJoinList(Role.class, wrapper);
    }

    @Override
    public IPage<Role> find(PagePlus<Role> page) {
        Role obj = page.getObj();
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Role.class)
                .like(StringUtils.isNotEmpty(obj.getRoleName()),Role::getRoleName,obj.getRoleName());
        return selectJoinListPage(page,Role.class, wrapper);
    }
}
