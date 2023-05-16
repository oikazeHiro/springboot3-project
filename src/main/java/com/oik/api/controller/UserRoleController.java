package com.oik.api.controller;

import com.oik.api.entity.UserRole;
import com.oik.api.service.UserRoleService;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色绑定 前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;
    @PostMapping
    public Result<UserRole> save(@RequestBody UserRole userRole){
        userRoleService.saveOne(userRole);
        return Result.ok(userRole);
    }
}
