package com.tims.core.image.mapper;

import com.tims.facade.domain.ImageInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageInfoMapper {

    /**
     * 新增图片
     * @param imageInfo
     * @return
     */
    public int saveImageInfo(ImageInfo imageInfo);

    /**
     * 更新图片
     * @param imageInfo
     * @return
     */
    public int updateImageInfo(ImageInfo imageInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageInfo(String id);
}
