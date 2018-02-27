package com.tims.common.controller;


import com.tims.common.result.ResultCode;
import com.tims.common.result.ResultVo;
import org.apache.log4j.Logger;

/**
 * 基控制器
 * @author: yecb
 * @create: 2017/11/13  19:36
 */
public class BaseController {
	private static final Logger log = Logger.getLogger(BaseController.class);

	protected ResultVo success=new ResultVo(ResultCode.SUCCESS);

}
