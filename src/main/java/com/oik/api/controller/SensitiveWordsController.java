package com.oik.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.api.entity.SensitiveWords;
import com.oik.api.service.SensitiveWordsService;
import com.oik.api.utils.pages.PagePlus;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 敏感词表 前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/sensitiveWords")
public class SensitiveWordsController {
    @Resource
    private SensitiveWordsService sensitiveWordsService;

    @PostMapping
    public Result<Page<SensitiveWords>> find(@RequestBody PagePlus<SensitiveWords> pagePlus){
        return Result.ok(sensitiveWordsService.find(pagePlus));
    }
    @PutMapping
    public Result<SensitiveWords> update(@RequestBody SensitiveWords sensitiveWords){
         sensitiveWordsService.saveOrUpdate(sensitiveWords);
         return Result.ok(sensitiveWords);
    }
    @DeleteMapping("/{id}")
    public Result<Boolean> del(@PathVariable("id") String id){
        return Result.ok(sensitiveWordsService.removeById(id));
    }
}
