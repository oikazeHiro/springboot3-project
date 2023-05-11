package com.oik.api.service.impl;

import com.oik.api.config.security.mobile.MobileUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 15093
 * @description 手机验证码登录
 * @date 2023/5/11 14:01
 */
@Service
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        return null;
    }
}
