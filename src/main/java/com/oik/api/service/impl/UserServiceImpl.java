package com.oik.api.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.Role;
import com.oik.api.entity.User;
import com.oik.api.mapper.UserMapper;
import com.oik.api.service.MenuService;
import com.oik.api.service.RoleService;
import com.oik.api.service.UserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.api.utils.dto.LoginDto;
import com.oik.api.utils.exception.ServerException;
import com.oik.api.utils.http.ErrorCode;
import com.oik.api.utils.jwt.AuthJwt;
import com.oik.api.utils.jwt.JwtUtils;
import com.oik.api.utils.jwt.RsaKeyProperties;
import com.oik.api.utils.redis.CacheClient;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.oik.api.utils.redis.RedisConstants.*;
import static com.oik.api.utils.redis.RedisConstants.CACHE_USER_PARAMS;

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
    @Resource
    private RsaKeyProperties rsaKeyProperties;
    @Resource
    private CacheClient cacheClient;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

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

    @Override
    @Transactional
    public User login(LoginDto loginDto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,loginDto.getUsername());
        User one = getOne(wrapper);
        verification(one,loginDto);
        generateInfo(one);
        return one;
    }

    @Override
    @Transactional
    public User loginByPhone(LoginDto loginDto) {
        verifictionCode(loginDto);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getMobile,loginDto.getPhone());
        User one = getOne(wrapper);
        verification(one,"手机号");
        generateInfo(one);
        return one;
    }



    @Override
    @Transactional
    public User loginByEmail(LoginDto loginDto) {
        verifictionCode(loginDto);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,loginDto.getEmail());
        User one = getOne(wrapper);
        verification(one,"邮箱");
        generateInfo(one);
        return one;
    }

    private void generateInfo(User user) {
        String userId = user.getId();
        List<Role> roles = cacheClient.getListValue(CACHE_USER_ROLE,userId, userId, Role.class, roleService::getByUserId);
        List<String> params = cacheClient.getListValue(CACHE_USER_PARAMS, userId, userId, String.class, menuService::getParams);
        user.setSysRole(roles);
        user.setParams(new HashSet<>(params));
        cacheClient.set(CACHE_USER_INFO+user.getId(),user);
        String uuid = userId+":"+IdUtil.fastUUID();
        user.setToken(uuid);
//        String token = JwtUtils.generateTokenExpireInDay(JSON.toJSONString(user), rsaKeyProperties.getPrivateKey(), 7);
        String token = AuthJwt.generateToken(user);
//        user.setToken(token);
        cacheClient.set(CACHE_USER_TOKEN+uuid,token,30L, TimeUnit.DAYS);
        generateLoginInfo(user);
    }

    /**
     * // todo 忘了要干什么了
     */
    private void generateLoginInfo(User user) {
    }

    private void verification(User one, LoginDto loginDto) {
        if (one == null){
            throw new ServerException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), one.getPassword());
        if (!matches){
            throw new ServerException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
    }

    private void verification(User one,String type){
        if (one == null){
            throw new ServerException(type+"未绑定");
        }
    }

    private void verifictionCode(LoginDto loginDto) {
        String captcha = cacheClient.getValue(CACHE_CAPTCHA, loginDto.getPhone());
        if (StringUtils.isEmpty(captcha)){
            throw new ServerException(ErrorCode.CAPTCHA_OVERDUE);
        }
        if (!captcha.equals(loginDto.getCode())){
            throw new ServerException(ErrorCode.CAPTCHA_OVERDUE);
        }
    }
}
