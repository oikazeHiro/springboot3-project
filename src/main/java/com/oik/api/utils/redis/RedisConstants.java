package com.oik.api.utils.redis;

public class RedisConstants {
    public static final int TOKEN_EXPIRED_TIME = 7;
    public static final String CACHE_USER_INFO = "oik:user:info:";
    public static final String CACHE_USER_ROLE = "oik:user:role:";
    public static final String CACHE_USER_PARAMS = "oik:user:params:";
    public static final String CACHE_USER_MENU = "oik:user:menu:";
    public static final String CACHE_USER_TOKEN = "oik:user:token:";
    public static final String CACHE_LOCK = "oik:lock:";
    public static final String CACHE_CAPTCHA = "oik:captcha:";
    public static final Long CACHE_MENU_TIME = 30L;
    public static final String CACHE_RULE = "oik:rule:";
    public static final String CACHE_RULE_LOCK = "oik:rule:lock:";
}
