package com.oik.api.utils.exception;

import com.oik.api.utils.result.Result;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.oik.api.utils.http.ErrorCode;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/12 17:22
 */
@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServerException.class)
    public Result<String> handleException(ServerException ex) {
        log.error(ex.getCode()+ex.getMsg());
        return Result.error(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        log.error(ex.getMessage());
        return Result.error(500,fieldError.getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return Result.error(ErrorCode.FORBIDDEN,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        log.error(ex.getMessage(),ex);
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Result<String> expiredJwtException(ExpiredJwtException e){
        log.error(e.getMessage());
        return Result.error(ErrorCode.TOKEN_ERROR,e.getMessage());
    }
}
