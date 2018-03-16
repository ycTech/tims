package com.tims.core.image.repository;

import com.tims.core.image.mapper.ImageClassifyRelMapper;
import com.tims.facade.domain.ImageClassifyRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageClassRelRepository {
    @Autowired
    private ImageClassifyRelMapper imageClassifyRelMapper;

    /**
     * 新增图片
     * @param imageClassifyRel
     * @return
     */
    public int saveImageClassifyRel(ImageClassifyRel imageClassifyRel){
        return  imageClassifyRelMapper.saveImageClassifyRel(imageClassifyRel);
    }

    /**
     * 更新图片
     * @param imageClassifyRel
     * @return
     */
    public int updateImageClassifyRel(ImageClassifyRel imageClassifyRel){
        return imageClassifyRelMapper.updateImageClassifyRel(imageClassifyRel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassifyRel(String id){
        return  imageClassifyRelMapper.deleteImageClassifyRel(id);
    }
}
