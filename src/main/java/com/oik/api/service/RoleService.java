package com.oik.api.service;

import com.oik.api.entity.Role;
import com.github.yulichang.base.service.MPJJoinService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
public interface RoleService extends MPJJoinService<Role> {

    List<Role> getByUserId(String userId);
}
