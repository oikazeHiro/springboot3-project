package com.oik.api.utils.jwt;

import com.oik.api.entity.Role;
import com.oik.api.entity.User;
import com.oik.api.service.MenuService;
import com.oik.api.service.RoleService;
import com.oik.api.service.UserService;
import com.oik.api.utils.redis.CacheClient;
import com.oik.api.utils.redis.UserHolder;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.oik.api.utils.redis.RedisConstants.*;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 10:52
 */
@Component
@AllArgsConstructor
public class TokenStoreCache {
    private final CacheClient cacheClient;

    private final RsaKeyProperties rsaKeyProperties;

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    public  void saveUser(User user, List<Role> roles, Set<String> params){
//        String token = JwtUtils.generateTokenExpireInDay(user, rsaKeyProperties.getPrivateKey(), TOKEN_EXPIRED_TIME);
        cacheClient.set(CACHE_USER_INFO+user.getId(),user);
        cacheClient.set(CACHE_USER_ROLE+user.getId(),roles);
        cacheClient.set(CACHE_USER_PARAMS+user.getId(),params);
    }

    public User getUser(String token){
        Payload<String> payload = JwtUtils.getInfoFromToken(token, rsaKeyProperties.getPublicKey(), String.class);
        String userId = payload.getUserInfo();
        User user = cacheClient.getValue(CACHE_USER_INFO, userId, userId, User.class, userService::getById);
        List<Role> roles = cacheClient.getListValue(CACHE_USER_ROLE,userId, userId, Role.class, roleService::getByUserId);
        List<String> params = cacheClient.getListValue(CACHE_USER_PARAMS, userId, userId, String.class, menuService::getParams);
        user.setSysRole(roles);
        user.setParams(new HashSet<>(params));
        UserHolder.saveUser(user);
        return user;
    }

    public void deleteUser(String token){

    }
}
