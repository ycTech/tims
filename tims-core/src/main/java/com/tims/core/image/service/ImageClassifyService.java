package com.tims.core.image.service;

import com.tims.core.image.repository.ImageClassifyRepository;
import com.tims.facade.domain.ImageClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageClassifyService {
    @Autowired
    private ImageClassifyRepository imageClassifyRepository;

    /**
     * 新增图片
     * @param imageClassify
     * @return
     */
    public int saveImageClassify(ImageClassify imageClassify){
        return  imageClassifyRepository.saveImageClassify(imageClassify);
    }

    /**
     * 更新图片
     * @param imageClassify
     * @return
     */
    public int updateImageClassify(ImageClassify imageClassify){
        return imageClassifyRepository.updateImageClassify(imageClassify);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassify(String id){
        return imageClassifyRepository.deleteImageClassify(id);
    }

    /**
     * 根据id获取图片类型
     * @param id
     * @return
     */
    public ImageClassify queryImageClassifyById(String id){
        return  imageClassifyRepository.queryImageClassifyById(id);
    }
}
