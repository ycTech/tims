package com.tims.keking.web.controller;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.ImageApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.tree.FileTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "单据管理")
@RestController
@RequestMapping("/preview")
public class PreviewBillController {

    @Autowired
    private ImageApiService imageApiService;

    @ApiOperation(value = "根据单据信息查询文件列表")
    @RequestMapping(value = "/file/list", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryFileListByBillInfo(@ RequestBody UploadQo uploadQo) throws Exception {
        FileTree fileTree= imageApiService.queryFileListByBillInfo(uploadQo);
        return ResultUtil.success(fileTree);
    }

}
