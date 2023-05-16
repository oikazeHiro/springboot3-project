package com.oik.api.controller;

import com.oik.api.entity.User;
import com.oik.api.service.UserService;
import com.oik.api.utils.dto.LoginDto;
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

    @PostMapping("/login/account")
    public Result<User> loginByAccount(@RequestBody LoginDto loginDto){
        return Result.ok(userService.login(loginDto));
    }

    @PostMapping("/login/phone")
    public Result<User> loginByPhone(@RequestBody LoginDto loginDto){
        return Result.ok(userService.loginByPhone(loginDto));
    }

    @PostMapping("/login/email")
    public Result<User> loginByEmail(@RequestBody LoginDto loginDto){
        return Result.ok(userService.loginByEmail(loginDto));
    }

}
