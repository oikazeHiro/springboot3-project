package com.oik.api.utils.jwt;

import lombok.Data;

import java.util.Date;

/**
 * @author Robod
 * @date 2020/8/9 23:04
 * 为了方便后期获取token中的用户信息，将token中载荷部分单独封装成一个对象
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}
