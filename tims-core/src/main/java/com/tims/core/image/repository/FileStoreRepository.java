package com.tims.core.image.repository;

import com.tims.core.image.mapper.FileStoreMapper;
import com.tims.core.image.mapper.ImageInfoMapper;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.domain.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileStoreRepository {
    @Autowired
    private FileStoreMapper fileStoreMapper;

    /**
     * 新增图片
     * @param fileStore
     * @return
     */
    public void savefileStore(FileStore fileStore){
        fileStoreMapper.saveFileStore(fileStore);
    }

    public List<FileStore> queryFileStore(UploadQo uploadQo) throws Exception{
        List<FileStore> fileStore=fileStoreMapper.queryFileStore(uploadQo);
        return fileStore;
    }
}
