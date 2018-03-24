package com.tims.manage.advice;

import com.tims.common.exception.BusinessException;
import com.tims.common.result.ResultCode;
import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常拦截
 * @author: yecb
 * @create: 2017/11/10  11:53
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    @Value("${spring.http.multipart.maxFileSize}")
    private String maxFileSize;

    @ExceptionHandler(BusinessException.class)
    public ResultVo handleResultException(BusinessException e, HttpServletRequest request) {
        logger.error("uri={} | requestBody={}", request.getRequestURI(), e);
        return ResultUtil.warn(e.getResultCode());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e, HttpServletRequest request) {
        logger.error("uri={} | requestBody={}", request.getRequestURI(), e);
        return ResultUtil.warn(ResultCode.EXCEPTION,e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ResultVo handleMultipartException(Exception e, HttpServletRequest request){
        logger.error("uri={} | requestBody={}", request.getRequestURI(), e);
        return ResultUtil.warn(ResultCode.EXCEPTION,"上传文件大小超过限定的大小！"+maxFileSize);
    }

    //添加校验异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo methodArgumentNotValidHandler(MethodArgumentNotValidException exception,HttpServletRequest request) throws Exception
    {
        StringBuffer stringBuffer=new StringBuffer();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            stringBuffer.append(error.getDefaultMessage()+";");
        }
        return ResultUtil.warn(ResultCode.EXCEPTION,stringBuffer.toString());
    }

}
