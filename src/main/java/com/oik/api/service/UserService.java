package com.oik.api.service;

import com.oik.api.entity.User;
import com.github.yulichang.base.MPJBaseService;
import com.oik.api.utils.dto.LoginDto;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-02
 */
public interface UserService extends MPJBaseService<User> {

    String test();

    void saveUser(User user);

    User login(LoginDto loginDto);

    User loginByPhone(LoginDto loginDto);

    User loginByEmail(LoginDto loginDto);
}
