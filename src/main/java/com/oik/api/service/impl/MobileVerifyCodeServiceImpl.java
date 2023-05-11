package com.oik.api.service.impl;

import com.oik.api.config.security.mobile.MobileVerifyCodeService;
import org.springframework.stereotype.Service;

/**
 * @author 15093
 * @description 短信验证码效验
 * @date 2023/5/11 14:03
 */
@Service
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    @Override
    public boolean verifyCode(String mobile, String code) {
        return false;
    }
}
