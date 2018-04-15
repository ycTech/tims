package com.tims.manage.controller;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.ImageApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.tree.File;
import com.tims.facade.tree.FileTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "单据管理")
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private ImageApiService imageApiService;

    @ApiOperation(value = "根据单据ID查询对应的图片列表")
    @RequestMapping(value = "/images/id/{billId}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryImagesByBillId(@PathVariable String billId) throws Exception {
//      String fileUrl= dfsClient.uploadFile(file);
        return null;
    }

    @ApiOperation(value = "根据单据ID查询对应的图片列表")
    @RequestMapping(value = "/images/billno/{billNo}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryImagesByBillNo(@PathVariable String billNo) throws Exception {
        Assert.hasText(billNo, "参数：员工Id不能为空！");
        BillImageVo billImageVo= imageApiService.queryImagesByBillNo(billNo);
        return ResultUtil.success(billImageVo);
    }

    @ApiOperation(value = "根据单据信息查询文件列表")
    @RequestMapping(value = "/file/list", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryFileListByBillInfo(@ RequestBody UploadQo uploadQo) throws Exception {
        FileTree fileTree= imageApiService.queryFileListByBillInfo(uploadQo);
        return ResultUtil.success(fileTree);
    }

}
