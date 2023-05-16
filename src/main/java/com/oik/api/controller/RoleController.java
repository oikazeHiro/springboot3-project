package com.oik.api.controller;

import com.oik.api.entity.Role;
import com.oik.api.service.RoleService;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping
    public Result<Role> save(@RequestBody Role role){
        roleService.save(role);
        return Result.ok(role);
    }

    @PutMapping
    public Result<Role> update(@RequestBody Role role){
        roleService.updateById(role);
        return Result.ok(role);
    }
}
