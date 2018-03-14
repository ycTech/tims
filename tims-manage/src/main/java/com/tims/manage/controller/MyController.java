package com.tims.manage.controller;

import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "fast测试")
@RestController
@RequestMapping("/fast")
public class MyController {
    @Autowired
    private FastDFSClientWrapper dfsClient;

    // 上传文件
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        return fileUrl;
    }

}
