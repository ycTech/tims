package com.tims.core.image.mapper;

import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.domain.ImageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileStoreMapper {

    /**
     * 新增文件存储信息
     * @param fileStore
     * @return
     */
    public void saveFileStore(FileStore fileStore);

}
