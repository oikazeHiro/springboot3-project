package com.oik.api.config.security.exception;

import com.oik.api.utils.http.ErrorCode;
import com.oik.api.utils.http.HttpContextUtils;
import com.oik.api.utils.jwt.JsonUtils;
import com.oik.api.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;
/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 10:16
 */
@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        response.setStatus(401);
        log.error(authException.getMessage());
        String string = JsonUtils.toString(Result.error(ErrorCode.UNAUTHORIZED));
        response.getWriter().print(string);
    }
}
