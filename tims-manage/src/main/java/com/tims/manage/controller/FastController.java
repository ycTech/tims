package com.tims.manage.controller;

import com.tims.facade.dfs.qo.UploadQo;
import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
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

    // 上传文件
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file,@RequestParam(value = "billNo") String billNo,@RequestParam(value = "billTypeId") String billTypeId,
            @RequestParam(value = "classifyId") String classifyId) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        return fileUrl;
    }

}
