package com.oik.api.controller;

import com.oik.api.entity.RoleMenu;
import com.oik.api.service.RoleMenuService;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色权限绑定 前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;
    @PostMapping
    public Result<RoleMenu> save(@RequestBody RoleMenu roleMenu){
        roleMenuService.saveOne(roleMenu);
        return Result.ok(roleMenu);
    }
}
