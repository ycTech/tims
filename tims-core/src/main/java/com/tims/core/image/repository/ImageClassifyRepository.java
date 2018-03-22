package com.tims.core.image.repository;

import com.tims.core.image.mapper.ImageClassifyMapper;
import com.tims.facade.domain.ImageClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageClassifyRepository {
    @Autowired
    private ImageClassifyMapper imageClassifyMapper;

    /**
     * 新增图片
     * @param imageClassify
     * @return
     */
    public int saveImageClassify(ImageClassify imageClassify){
        return  imageClassifyMapper.saveImageClassify(imageClassify);
    }

    /**
     * 更新图片
     * @param imageClassify
     * @return
     */
    public int updateImageClassify(ImageClassify imageClassify){
        return imageClassifyMapper.updateImageClassify(imageClassify);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassify(String id){
        return imageClassifyMapper.deleteImageClassify(id);
    }

    /**
     * 根据id获取图片类型
     * @param id
     * @return
     */
    public ImageClassify queryImageClassifyById(String id){
        return  imageClassifyMapper.queryImageClassifyById(id);
    }
}
