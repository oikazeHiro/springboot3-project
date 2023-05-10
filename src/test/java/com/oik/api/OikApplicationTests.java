package com.oik.api;

import com.alibaba.fastjson2.JSON;
import com.oik.api.service.UserService;
import com.oik.api.utils.jwt.RsaUtils;
import com.oik.api.utils.pages.PagePlus;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OikApplicationTests {

    @Resource
    private UserService userService;

    @Value("${rsa.key.pubKeyPath}")
    private String path;
    @Test
    void contextLoads() throws Exception {
//        RsaUtils.generateKey("E:/auth_key/id_key_rsa.pub","E:/auth_key/id_key_rsa","oiazke",20);
        System.out.println("path = " + path);
    }

}
