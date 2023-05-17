package com.oik.api.controller;

import com.oik.api.entity.Dict;
import com.oik.api.service.DictService;
import com.oik.api.utils.result.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    private DictService dictService;

    @GetMapping
    public Result<List<Dict>> dictAll(){
        return Result.ok(dictService.dictAll());
    }

    @PostMapping
    public Result<Dict> save(@RequestBody Dict dict){
        dictService.saveOne(dict);
        return Result.ok(dict);
    }

    @GetMapping("/map")
    public Result<Map<String,List<Dict>>> dictMap(){
        return Result.ok(dictService.dictAllMap());
    }
}
