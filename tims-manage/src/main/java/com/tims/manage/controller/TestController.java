package com.tims.manage.controller;

import com.tims.common.controller.BaseController;
import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.TestApiService;
import com.tims.facade.domain.Test;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuzm
 * @create 2018-02-27 18:39
 **/

@Api(value = "测试用例")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private TestApiService testApiService;
    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/page",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<List<Test>> queryPageRoleInfo() throws  Exception{
        List<Test> list=testApiService.queryTestList();
        return ResultUtil.success(list);
    }

}
