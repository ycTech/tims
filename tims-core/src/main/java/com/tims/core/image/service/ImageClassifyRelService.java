package com.tims.core.image.service;

import com.tims.core.image.repository.ImageClassRelRepository;
import com.tims.facade.domain.ImageClassify;
import com.tims.facade.domain.ImageClassifyRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageClassifyRelService {
    @Autowired
    private ImageClassRelRepository imageClassRelRepository;

    /**
     * 新增图片
     * @param imageClassifyRel
     * @return
     */
    public int saveImageClassifyRel(ImageClassifyRel imageClassifyRel){
        return  imageClassRelRepository.saveImageClassifyRel(imageClassifyRel);
    }

    /**
     * 更新图片
     * @param imageClassifyRel
     * @return
     */
    public int updateImageClassifyRel(ImageClassifyRel imageClassifyRel){
        return imageClassRelRepository.updateImageClassifyRel(imageClassifyRel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassifyRel(String id){
        return  imageClassRelRepository.deleteImageClassifyRel(id);
    }

    /**
     * 根据单据类型id获取图片类型列表
     * @param billTypeId
     * @return
     */
    public List<ImageClassifyRel> queryImageClassifyRelByBillTypeId(String billTypeId){
        return  imageClassRelRepository.queryImageClassifyRelByBillTypeId(billTypeId);
    }
}
