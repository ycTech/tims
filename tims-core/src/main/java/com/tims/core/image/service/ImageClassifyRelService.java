package com.tims.core.image.service;

import com.tims.facade.domain.ImageClassify;
import com.tims.facade.domain.ImageClassifyRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageClassifyRelService {
    @Autowired
    private ImageClassifyRelService imageClassifyRelService;

    /**
     * 新增图片
     * @param imageClassifyRel
     * @return
     */
    public int saveImageClassifyRel(ImageClassifyRel imageClassifyRel){
        return  imageClassifyRelService.saveImageClassifyRel(imageClassifyRel);
    }

    /**
     * 更新图片
     * @param imageClassifyRel
     * @return
     */
    public int updateImageClassifyRel(ImageClassifyRel imageClassifyRel){
        return imageClassifyRelService.updateImageClassifyRel(imageClassifyRel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassifyRel(String id){
        return  imageClassifyRelService.deleteImageClassifyRel(id);
    }
}
