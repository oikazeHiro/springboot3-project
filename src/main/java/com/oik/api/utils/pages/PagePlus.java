package com.oik.api.utils.pages;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.*;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/5 9:52
 */
public class PagePlus<T> extends Page<T> {
    protected T obj;
    protected Map<String,String> other;

    public PagePlus(){
        super();
        this.other = new HashMap<>();
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public Map<String, String> getOther() {
        return other;
    }

    public void setOther(Map<String, String> other) {
        this.other = other;
    }
}
