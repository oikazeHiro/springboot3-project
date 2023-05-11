package com.oik.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 14:12
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/t1")
    public String ti(){
        return "t1";
    }
}
