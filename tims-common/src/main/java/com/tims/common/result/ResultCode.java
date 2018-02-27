package com.tims.common.result;

/**
 * 结果
 * @author yecb
 * @create 2017/09/26 11:36
 */
public enum ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 异常
     */
    EXCEPTION(500, "异常");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
