package com.tims.core.image.mapper;

import com.github.pagehelper.Page;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.domain.ImageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface FileStoreMapper {

    /**
     * 新增文件存储信息
     * @param fileStore
     * @return
     */
    public void saveFileStore(FileStore fileStore);

    public void updateFileStore(FileStore fileStore);

    /**
     * 查询文件存储信息
     * @param uploadQo
     * @return
     */
    List<FileStore> queryFileStore(@Param("uploadQo") UploadQo uploadQo) ;

    /**
     * 查询影像列表
     * @param fileStore
     * @return
     */
    Page<FileStore> queryFileList(@Param("fileStore") FileStore fileStore) ;

    public  int  deleteFileInfoByPath(@Param("filePath") String  filePath);

    public  List<FileStore> queryFileStoreByPath(@Param("path") String path);
}
