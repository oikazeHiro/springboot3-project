package com.oik.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.User;
import com.oik.api.mapper.UserMapper;
import com.oik.api.service.UserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
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

    @Override
    public String test() {

        return "null";
    }
}
