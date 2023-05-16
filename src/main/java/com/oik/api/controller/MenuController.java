package com.oik.api.controller;

import com.oik.api.entity.Menu;
import com.oik.api.service.MenuService;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping
    public Result<Menu> save(@RequestBody Menu menu){
        menuService.save(menu);
        return Result.ok(menu);
    }

    @PutMapping
    public Result<Menu> update(@RequestBody Menu menu){
        menuService.updateById(menu);
        return Result.ok(menu);
    }

}
