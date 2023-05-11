package com.oik.api.utils.jwt;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 10:50
 */
public class TokenUtils {
    public static String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StrUtil.isBlank(accessToken)) {
            accessToken = request.getParameter("access_token");
        }
        return accessToken;
    }
}
