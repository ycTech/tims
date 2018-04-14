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
    private FastDFSFileStorageClient fastDFSFileStorageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile((InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return getResAccessUrl(storePath);
    }

    /**
     * 指定分组上传（预览的时候使用）
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFileByGroup(String groupName,MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(groupName,(InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()));
        return getResAccessUrl(storePath);
    }

    /**
     * 指定分组上传BASE64（预览的时候使用）
     * @param groupName
     * @param imgStr
     * @param fileName
     * @return
     * @throws IOException
     */
    public String uploadFileByGroup(String groupName,String imgStr,String fileName) throws IOException {
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
        InputStream input = new ByteArrayInputStream(b);
        StorePath storePath = storageClient.uploadFile(groupName,input,b.length, FilenameUtils.getExtension(fileName));
        return getResAccessUrl(storePath);
    }

    /**
     * 指定分组上传BASE64,生成缩略图（预览的时候使用）
     * @param groupName
     * @param imgStr
     * @param fileName
     * @return
     * @throws IOException
     */
    public String[] uploadThumbImageByGroup(String groupName,String imgStr,String fileName) throws IOException {
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
        InputStream input = new ByteArrayInputStream(b);
        String[] storePath = new String[2];
        StorePath[] storePaths = fastDFSFileStorageClient.uploadImageAndCrtThumbImage(groupName,input,b.length, FilenameUtils.getExtension(fileName),null);
        storePath[0]=getResAccessUrl(storePaths[0]);
        storePath[1]=getResAccessUrl(storePaths[1]);
        return storePath;
    }

    public String uploadFile(String imgStr,String fileName) throws IOException {
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
        InputStream input = new ByteArrayInputStream(b);
        StorePath storePath = storageClient.uploadFile(input,b.length, FilenameUtils.getExtension(fileName),null);
        return getResAccessUrl(storePath);
    }

    // 封装文件完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl =  fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        return fileUrl;
    }

    public void delFile(String fileUrl){
        StorePath storePath = StorePath.praseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }

}
