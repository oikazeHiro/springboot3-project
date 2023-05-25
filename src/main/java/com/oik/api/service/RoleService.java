package com.oik.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oik.api.entity.Role;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.api.utils.pages.PagePlus;

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

    IPage<Role> find(PagePlus<Role> page);
}
