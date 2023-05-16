package com.oik.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.User;
import com.oik.api.mapper.UserMapper;
import com.oik.api.service.UserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.api.utils.dto.LoginDto;
import com.oik.api.utils.exception.ServerException;
import com.oik.api.utils.http.ErrorCode;
import com.oik.api.utils.jwt.JwtUtils;
import com.oik.api.utils.jwt.RsaKeyProperties;
import com.oik.api.utils.redis.CacheClient;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.oik.api.utils.redis.RedisConstants.CACHE_CAPTCHA;
import static com.oik.api.utils.redis.RedisConstants.CACHE_USER_INFO;

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
        cacheClient.set(CACHE_USER_INFO+user.getId(),user);
        String token = JwtUtils.generateTokenExpireInDay(user.getId(), rsaKeyProperties.getPrivateKey(), 7);
        user.setToken(token);
        generateLoginInfo(user);
    }

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
