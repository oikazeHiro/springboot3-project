package com.oik.api.utils.redis;

public class RedisConstants {
    public final static int TOKEN_EXPIRED_TIME = 7;
    public final static String CACHE_USER_INFO = "oik:user:info:";
    public final static String CACHE_USER_ROLE = "oik:user:role:";
    public final static String CACHE_USER_PARAMS = "oik:user:params:";
    public final static String CACHE_USER_MENU= "oik:user:menu:";
    public final static String CACHE_USER_TOKEN= "oik:user:token:";
    public final static String CACHE_LOCK = "oik:lock:";
    public final static String CACHE_CAPTCHA = "oik:captcha";
    public final static Long CACHE_MENU_TIME = 30L;
}
