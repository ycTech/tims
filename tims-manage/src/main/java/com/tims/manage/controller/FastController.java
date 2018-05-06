package com.tims.manage.controller;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.BillApiService;
import com.tims.facade.api.FileStoreApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Api(value = "FASTDFS上传接口")
@RestController
@RequestMapping("/fast")
public class FastController extends BaseController {
    @Autowired
    private FastDFSClientWrapper dfsClient;
    @Autowired
    private BillApiService billApiService;
    @Autowired
    private FileStoreApiService fileStoreApiService;


    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo upload(@RequestParam("file")MultipartFile file,
                           @RequestParam(value = "userCode") String billNo,
                           @RequestParam(value = "billTypeId") String billTypeId,
                           @RequestParam(value = "classifyId") String classifyId
  ) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        UploadQo uploadQos=new UploadQo();
        uploadQos.setBillNo(billNo);
//        uploadQos.setBillTypeId(billTypeId);
//        uploadQos.setClassifyId(classifyId);
        uploadQos.setImageUrl(fileUrl);
        uploadQos.setImageName(file.getOriginalFilename());
        billApiService.saveBillImage(uploadQos);
        return ResultUtil.success(fileUrl);
    }

    @ApiOperation(value = "上传BASE64文件")
    @RequestMapping(value = "/base64/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo uploadBase64(@Validated @RequestBody UploadQo uploadQo) throws Exception {
        Assert.hasText(uploadQo.getPath(), "参数：目录不能为空！");
        Assert.hasText(uploadQo.getUserCode(), "参数：制单人不能为空！");
        String fileUrl= dfsClient.uploadFile(uploadQo.getImageBase64(),uploadQo.getImageName());
        uploadQo.setImageUrl(fileUrl);
        fileStoreApiService.saveFileStore(uploadQo);
        return ResultUtil.success(fileUrl);
    }

    @ApiOperation(value = "上传BASE64文件-预览使用(指定了分组)")
    @RequestMapping(value = "/group/base64/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo uploadBase64ByGroup(@Validated @RequestBody UploadQo uploadQos) throws Exception {
        Assert.hasText(uploadQos.getPath(), "参数：目录不能为空！");
        Assert.hasText(uploadQos.getUserCode(), "参数：制单人不能为空！");
        String fileUrl= dfsClient.uploadFileByGroup("group1",uploadQos.getImageBase64(),uploadQos.getImageName());
        String[] thumbImageUrls=dfsClient.uploadThumbImageByGroup("group1",uploadQos.getImageBase64(),uploadQos.getImageName());
        Map<String,String> urlMap=new HashMap<String,String>();
        urlMap.put("fileUrl",thumbImageUrls[0]);
        urlMap.put("thumbImageUrl",thumbImageUrls[1]);
        return ResultUtil.success(urlMap);
    }

    @ApiOperation(value = "删除文件")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultVo deleteFile(@RequestParam String fileUrl) throws Exception {
        dfsClient.delFile(fileUrl);
        return success;
    }

}
