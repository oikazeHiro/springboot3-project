package com.oik.api.utils.pages;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.*;

/**
 * @author oik
 * @description 继承 mybatis-plus 的自定义分页插件
 * @date 2023/5/5 9:52
 */
public class PagePlus<T> extends Page<T> {
    protected T obj; // 一般查询条件
    protected Map<String,String> other; // 普通查询条件

    public PagePlus(){
        super();
        this.other = new HashMap<>();
    }

    public T getObj() {
        return obj;
    }

    public PagePlus<T> setObj(T obj) {
        this.obj = obj;
        return this;
    }

    public Map<String, String> getOther() {
        return other;
    }

    public PagePlus<T> setOther(Map<String, String> other) {
        this.other = other;
        return this;
    }
}
