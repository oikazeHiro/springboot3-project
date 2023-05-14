package com.oik.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.User;
import com.oik.api.mapper.UserMapper;
import com.oik.api.service.UserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.api.utils.exception.ServerException;
import com.oik.api.utils.http.ErrorCode;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-02
 */
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String test() {

        return "null";
    }

    @Override
    public void saveUser(User user) {
        if(StringUtils.isEmpty(user.getPassword())){
            throw new ServerException(ErrorCode.PASSWORD_IS_NOT_NULL);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
    }
}
