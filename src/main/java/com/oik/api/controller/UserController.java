package com.oik.api.controller;

import com.oik.api.entity.User;
import com.oik.api.service.UserService;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping
    public String test(){
        return "test";
    }

    @PostMapping
    public Result<String> save(@RequestBody User user){
        userService.saveUser(user);
        return Result.ok();
    }
}
