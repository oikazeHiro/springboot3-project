package com.oik.api.service.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.api.entity.Role;
import com.oik.api.entity.UserRole;
import com.oik.api.mapper.RoleMapper;
import com.oik.api.service.RoleService;
import com.github.yulichang.base.MPJBaseServiceImpl;
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
}
