package com.tims.core.image.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
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

    public Page<FileStore> queryFileList(FileStore fileStore) throws Exception{
        if("Y".equals(fileStore.getIsPage())) {
            fileStore.enablePaging();
        }
        Page<FileStore> fileStoreList=fileStoreMapper.queryFileList(fileStore);
//        PageInfo<FileStore> pageInfo = new PageInfo<>(fileStoreList);
        return fileStoreList;
    }
}
