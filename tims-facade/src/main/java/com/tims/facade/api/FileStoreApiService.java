package com.tims.facade.api;

import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.hessian.HessianService;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

@HessianService(uri = "/fileStoreApiService")
public interface FileStoreApiService {
    /**
     * 保存文件
     *
     * @param uploadQos
     */
    public void saveFileStore(UploadQo uploadQos) throws Exception;

    /**
     * 查询上传文件
     *
     * @param uploadQo
     * @return
     */
    public List<FileStore> queryUrlByPath(UploadQo uploadQo) throws Exception;

    /**
     * 根据path删除文件
     * @param filePath
     * @return
     */
    public Boolean deleteFileInfoByPath(String filePath);

    /**
     * 通过path获取文件信息
     * @param path
     * @return
     */
    public  List<FileStore> queryFileStoreByPath( String path);
}