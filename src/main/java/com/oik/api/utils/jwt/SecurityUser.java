package com.oik.api.utils.jwt;

import com.oik.api.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 9:57
 */
public class SecurityUser {
    /**
     * 获取用户信息
     */
    public static User getUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return new User();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static String getUserId() {
        return getUser().getId();
    }
}
