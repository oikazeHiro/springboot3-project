package com.oik.api.service;

import com.oik.api.entity.Menu;
import com.github.yulichang.base.service.MPJJoinService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
public interface MenuService extends MPJJoinService<Menu> {


    List<String> getParams(String userId);
}
