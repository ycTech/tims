package com.tims.core.image.service;

import com.tims.common.util.PkUtil;
import com.tims.core.image.repository.FileStoreRepository;
import com.tims.core.image.repository.ImageInfoRepository;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.domain.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileStoreService {
    @Autowired
    private FileStoreRepository fileStoreRepository;

    /**
     * 新增文件存储
     * @param fileStore
     * @return
     */
    public void saveImageInfo(FileStore fileStore){
        fileStore.setId(PkUtil.getUUID());
        fileStore.setCreateTime(new Date());
        fileStoreRepository.savefileStore(fileStore);
    }

    public List<FileStore> queryUrlByPath(UploadQo uploadQo) throws Exception {
        List<FileStore> fileStoreList=fileStoreRepository.queryFileStore(uploadQo);
        return fileStoreList;
    }

    public  Boolean  deleteFileInfoByPath(String filePath){
        if(fileStoreRepository.deleteFileInfoByPath(filePath)>0){
            return  true;
        }else {
            return false;
        }
    }
}
