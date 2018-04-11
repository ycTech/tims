package com.tims.manage.controller;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.BillApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "fastDFS上传")
@RestController
@RequestMapping("/fast")
public class FastController {
    @Autowired
    private FastDFSClientWrapper dfsClient;
    @Autowired
    private BillApiService billApiService;

    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo upload(@RequestParam("file")MultipartFile file,
                           @RequestParam(value = "billNo") String billNo,
                           @RequestParam(value = "billTypeId") String billTypeId,
                           @RequestParam(value = "classifyId") String classifyId) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        UploadQo uploadQos=new UploadQo();
        uploadQos.setBillNo(billNo);
        uploadQos.setBillTypeId(billTypeId);
        uploadQos.setClassifyId(classifyId);
        uploadQos.setImageUrl(fileUrl);
        uploadQos.setImageName(file.getOriginalFilename());
        billApiService.saveBillImage(uploadQos);
        return ResultUtil.success(fileUrl);
    }

    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/base64/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo upload(@RequestBody UploadQo uploadQos) throws Exception {
        uploadQos.setImageBase64("iVBORw0KGgoAAAANSUhEUgAAAFgAAAA/CAYAAABkZA/WAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAJnSURBVHhe7ZndsYMgFITtxnIsx7IsxyLykBK4mEiC/LlyszOKe2d8urAjn8tyDumM/qgEOqq6xI0Ak00AAX48Hib1kN+tCXkBJn9GARZgMgGyvBwswGQCZHk5WIDJBMjycrAAkwn8RH42Y9+ZruvNOG8F5eAS4Gmw0Cy4fjQBt2BWS4DdopeF/+gZpgxlAf4NZAH2Dba6Kg1lMoN1dR8GYcag89i/doEAQ4BdDlrAllg5M9+CApxyXtLBX7hILjvHCjAE+B0LCFg35jDgg/qbd0HKwFNduPsOjiqKuA5163Nu7bzAhR18V8CTPaT6ZfErtGlYneyfWvP4HtMNJqzGYMC7dXDeptdrNIpVhF1o5OoYbOjq/1cRNwH8iYHClt6aGyzTsl9gP2Av7ODC4eYDyXV+dtsvEQPVwfcEvKTBmrkuIwOYccPh1cq2GYEz+NAhtz1oL+zgeHuWI+L4IYdETlweNgw4RP5xuFdp+GP2HOzmY613+katAQfHXRwGZK9V/upiEdws4IOdHNpoFOrndO3QLOBwueV7CbRMSzYtxarsNoALFGZbOXj/zmawV41g8fC6m0v+bHTZDD5yubM3dgPxEw3fFny/nRDg4m3bti9xN3P5FvvmGYz5LX/hvmz1/K1cXr2ViMD5nWLk9TL4FNjwlxBgnFXVSAGuwoZPEmCcVdVIAa7Chk8SYJxV1UgBrsKGT4IA43IaGRIQYLInBFiAyQTI8nKwAJMJkOXlYAEmEyDLy8ECTCZAlpeDBZhMgCwvBwswmQBZXg4+A+Dn82n01DGQg8/gYPI7NC0vB5M/rwCTAf8B4VyRHlZFvnUAAAAASUVORK5CYII=");
        String fileUrl= dfsClient.uploadFile(uploadQos.getImageBase64(),uploadQos.getImageName(),null);
        uploadQos.setImageUrl(fileUrl);
        billApiService.saveBillImage(uploadQos);
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
