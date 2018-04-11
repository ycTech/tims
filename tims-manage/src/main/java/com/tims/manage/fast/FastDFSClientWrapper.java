package com.tims.manage.fast;

import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;

/**
 * <p>Description: FastDFS文件上传下载包装类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * @author 杨信
 * @version 1.0
 * @date 2016/9/7
 */
@Component
public class FastDFSClientWrapper {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile((InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getResAccessUrl(storePath);
    }

    public String uploadFile(String imgStr,String fileName,Long fileSize) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] b = decoder.decodeBuffer(imgStr);
        for(int i=0;i<b.length;++i)
        {
            if(b[i]<0)
            {//调整异常数据
                b[i]+=256;
            }
        }
        //生成jpeg图片
//        String imgFilePath = "D:\\ImageShow\\new.jpg";//新生成的图片
//        OutputStream out = new FileOutputStream(imgFilePath);
//        out.write(b);
//        out.flush();
//        out.close();
        InputStream input = new ByteArrayInputStream(b);
        StorePath storePath = storageClient.uploadFile(input,b.length, FilenameUtils.getExtension(fileName),null);
        return getResAccessUrl(storePath);
    }

    // 封装文件完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        return fileUrl;
    }

    /**
     *通过图片base64流判断图片等于多少字节
     *image 图片流
     */
    public static Integer imageSize(String image){
        Integer equalIndex= image.indexOf("=");//2.找到等号，把等号也去掉
        if(image.indexOf("=")>0) {
            image=image.substring(0, equalIndex);
        }
        Integer strLength=image.length();//3.原来的字符流大小，单位为字节
        Integer size=strLength-(strLength/8)*2;//4.计算后得到的文件流大小，单位为字节
        return size/1024;
    }

}
