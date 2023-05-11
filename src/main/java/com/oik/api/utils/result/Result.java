package com.oik.api.utils.result;

import com.oik.api.utils.http.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/11 10:22
 */
@Data
@Schema(description = "响应")
public class Result<T> {
    private int code = 200;
    private String msg = "success";
    private T data;

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode){
        return error(errorCode.getCode(),errorCode.getMsg());
    }

    public static <T> Result<T> error(ErrorCode errorCode,T data){
        return ok(errorCode.getCode(),errorCode.getMsg(),data);
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ok(int code, String msg,T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(int code,T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(String msg,T data) {
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
