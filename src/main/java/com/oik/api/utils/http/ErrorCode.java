package com.oik.api.utils.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误编码
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "还未授权，不能访问"),
    FORBIDDEN(403, "没有权限，禁止访问"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    PASSWORD_IS_NOT_NULL(400, "密码不能为空"),
    ACCOUNT_OR_PASSWORD_ERROR(500, "用户名或密码错误"),
    CAPTCHA_OVERDUE(500, "验证码无效"),
    TOKEN_ERROR(401, "令牌验证失败");

    private final int code;
    private final String msg;
}
