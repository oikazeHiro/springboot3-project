package com.oik.api.service;

import com.oik.api.entity.User;
import com.github.yulichang.base.service.MPJJoinService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-02
 */
public interface UserService extends MPJJoinService<User> {

    String test();

    void saveUser(User user);
}
