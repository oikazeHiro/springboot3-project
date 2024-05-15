package com.oik.api.service;

import com.oik.api.entity.RoleMenu;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 角色权限绑定 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
public interface RoleMenuService extends MPJBaseService<RoleMenu> {

    void saveOne(RoleMenu roleMenu);
}
