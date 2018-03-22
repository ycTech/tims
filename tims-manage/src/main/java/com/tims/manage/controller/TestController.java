package com.tims.manage.controller;

import com.tims.common.controller.BaseController;
import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.TestApiService;
import com.tims.facade.api.TimsApiService;
import com.tims.facade.domain.BillType;
import com.tims.facade.domain.ImageClassify;
import com.tims.facade.domain.Test;
import com.tims.facade.domain.vo.BillInfoVo;
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
    @Autowired
    private TimsApiService timsApiService;

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/page",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<List<Test>> queryPageRoleInfo() throws  Exception{
        List<Test> list=testApiService.queryTestList();
        return ResultUtil.success(list);
    }

    @ApiOperation(value = "根据单据ID查询对应详细信息")
    @RequestMapping(value = "/bill/{billNo}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryBillInfoVoByBillId(@PathVariable String billNo) throws Exception {
        BillInfoVo billInfoVo=timsApiService.queryBillInfoByBillNo(billNo);
        return  ResultUtil.success(billInfoVo);
    }

    @ApiOperation(value = "测试同步单据类型")
    @RequestMapping(value = "/saveImageClassify",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Boolean> insertBillType(@RequestBody BillType billType) throws Exception {
        Boolean result=timsApiService.synchronizeBillType(billType);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "测试同步图片类型")
    @RequestMapping(value = "/saveBIllType",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Boolean> insertImageClassify(@RequestBody ImageClassify imageClassify) throws Exception {
        Boolean result=timsApiService.synchronizeImageClassify(imageClassify);
        return ResultUtil.success(result);
    }
}
