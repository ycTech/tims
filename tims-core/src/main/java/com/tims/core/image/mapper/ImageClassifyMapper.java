package com.tims.core.image.mapper;

import com.tims.facade.domain.ImageClassify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageClassifyMapper {

    /**
     * 新增图片
     * @param imageClassify
     * @return
     */
    public int saveImageClassify(ImageClassify imageClassify);

    /**
     * 更新图片
     * @param imageClassify
     * @return
     */
    public int updateImageClassify(ImageClassify imageClassify);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassify(String id);

}
