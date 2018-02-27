package com.tims.common.util;


import com.tims.common.result.ResultCode;
import com.tims.common.result.ResultVo;

/**
 * 返回结果的工具类
 * @author: liuzm
 * @create: 2017/11/10  11:57
 */
public class ResultUtil {

    public static ResultVo success(Object data) {
        return new ResultVo<>(ResultCode.SUCCESS, data);
    }

    public static ResultVo warn(ResultCode resultCode, String msg) {
        ResultVo<Object> result = new ResultVo<>(resultCode);
        result.setMsg(msg);
        return result;
    }

    public static ResultVo warn(ResultCode resultCode) {
        return new ResultVo(resultCode);
    }

}
