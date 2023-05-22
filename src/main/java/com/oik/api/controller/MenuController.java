package com.oik.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oik.api.entity.Menu;
import com.oik.api.service.MenuService;
import com.oik.api.utils.pages.PagePlus;
import com.oik.api.utils.redis.UserHolder;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        menuService.saveOne(menu);
        return Result.ok(menu);
    }

    @PutMapping
    public Result<Menu> update(@RequestBody Menu menu){
        menuService.updateOne(menu);
        return Result.ok(menu);
    }

    @GetMapping("/self")
    public Result<List<Menu>> menuBySelf(){
        return Result.ok(menuService.getMenuByUser(UserHolder.getUser().getId()));
    }

    @PostMapping("/find")
    public Result<IPage<Menu>> find(@RequestBody PagePlus<Menu> pagePlus){
        return Result.ok(menuService.find(pagePlus));
    }
}
