package com.tims.common.result;

/**
 * 结果数据
 * @author yecb
 * @create 2017/9/26
 */
public class ResultVo<T> {

    private int code;
    private String msg;
    private T data;

    public ResultVo() {
    }

    public ResultVo(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public ResultVo(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
