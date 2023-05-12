package com.oik.api.utils.exception;

import com.oik.api.utils.http.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/12 17:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
    private Integer code = 500;
    private String msg = "error";

    public ServerException(String msg){
        super(msg);
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

}
