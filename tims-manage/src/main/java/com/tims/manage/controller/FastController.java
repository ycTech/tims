package com.tims.manage.controller;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "fastDFS上传")
@RestController
@RequestMapping("/fast")
public class FastController {
    @Autowired
    private FastDFSClientWrapper dfsClient;

    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo upload(@RequestParam("file")MultipartFile file, @RequestParam(value = "billNo") String billNo, @RequestParam(value = "billTypeId") String billTypeId,
                           @RequestParam(value = "classifyId") String classifyId) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        return ResultUtil.success(fileUrl);
    }

    // 下载文件
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String load(@RequestParam("file")MultipartFile file,@RequestParam(value = "billNo") String billNo,@RequestParam(value = "billTypeId") String billTypeId,
                         @RequestParam(value = "classifyId") String classifyId) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        return fileUrl;
    }

}
