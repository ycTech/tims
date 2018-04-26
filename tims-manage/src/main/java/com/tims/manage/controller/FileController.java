package com.tims.manage.controller;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.ImageApiService;
import com.tims.facade.bill.qo.FileStoreQo;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.tree.FileTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Api(value = "影像管理")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ImageApiService imageApiService;

    @ApiOperation(value = "影像列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryFileList(@RequestBody FileStore fileStore) throws Exception {
        return ResultUtil.success(imageApiService.queryFileStoreList(fileStore));
    }

}
