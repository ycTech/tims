package com.tims.core.api;

import com.tims.core.image.service.FileStoreService;
import com.tims.facade.api.FileStoreApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileStoreApiServiceImpl implements FileStoreApiService {

    @Autowired
    private FileStoreService fileStoreService;

    @Override
    public void saveFileStore(UploadQo uploadQos) {
        FileStore fileStore=new FileStore();
        fileStore.setBillId(uploadQos.getBillId());
        fileStore.setFilePath(uploadQos.getPath());
        fileStore.setBillNo(uploadQos.getBillNo());
        fileStore.setBillType(uploadQos.getBillType());
        fileStore.setUrl(uploadQos.getImageUrl());
        fileStore.setUserCode(uploadQos.getUserCode());
        fileStore.setImageName(uploadQos.getImageName());
        fileStore.setFileSize(uploadQos.getFileSize());
        fileStore.setIsDelete("0");
        fileStoreService.saveImageInfo(fileStore);
    }

    @Override
    public List<FileStore> queryUrlByPath(UploadQo uploadQo) throws Exception {
        return fileStoreService.queryUrlByPath(uploadQo);
    }

    @Override
    public Boolean deleteFileInfoByPath(String filePath) {
        return fileStoreService.deleteFileInfoByPath(filePath);
    }


}
