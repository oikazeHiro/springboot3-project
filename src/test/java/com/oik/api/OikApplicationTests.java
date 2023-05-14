package com.oik.api;

import com.alibaba.fastjson2.JSON;
import com.oik.api.entity.Role;
import com.oik.api.service.RoleService;
import com.oik.api.service.UserService;
import com.oik.api.utils.jwt.RsaUtils;
import com.oik.api.utils.pages.PagePlus;
import com.oik.api.utils.redis.CacheClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import static com.oik.api.utils.redis.RedisConstants.CACHE_USER_INFO;
import static com.oik.api.utils.redis.RedisConstants.CACHE_USER_ROLE;

@SpringBootTest
class OikApplicationTests {

    @Test
    void contextLoads() {
    }

}
