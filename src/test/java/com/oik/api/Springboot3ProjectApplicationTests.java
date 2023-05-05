package com.oik.api;

import com.alibaba.fastjson2.JSON;
import com.oik.api.service.UserService;
import com.oik.api.utils.pages.PagePlus;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot3ProjectApplicationTests {

    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
    }

}
