package com.tims.common.exception;


import com.tims.common.result.ResultCode;

/**
 * 结果异常，会被 ExceptionHandler 捕捉并返回给前端
 * @author : yecb
 * @create: 2017/11/10  11:54
 */
public class BusinessException extends RuntimeException {

    private ResultCode resultCode;

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
