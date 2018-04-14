package com.tims.facade.api;

import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.hessian.HessianService;

@HessianService(uri = "/fileStoreApiService")
public interface FileStoreApiService {
    /**
     * 保存文件
     * @param uploadQos
     */
    public void saveFileStore(UploadQo uploadQos);
}
