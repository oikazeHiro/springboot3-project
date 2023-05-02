package com.oik.api;

import com.oik.api.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot3ProjectApplicationTests {

    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
        String test = userService.test();
        System.out.println(test);
    }

}
