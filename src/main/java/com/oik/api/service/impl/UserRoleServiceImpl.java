package com.oik.api.service.impl;

import com.oik.api.entity.UserRole;
import com.oik.api.mapper.UserRoleMapper;
import com.oik.api.service.UserRoleService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色绑定 服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Service
public class UserRoleServiceImpl extends MPJBaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
