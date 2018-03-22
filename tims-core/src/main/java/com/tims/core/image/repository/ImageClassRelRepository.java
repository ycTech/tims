package com.tims.core.image.repository;

import com.tims.core.image.mapper.ImageClassifyRelMapper;
import com.tims.facade.domain.ImageClassifyRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    /**
     * 根据单据类型id获取图片类型列表
     * @param billTypeId
     * @return
     */
    public List<ImageClassifyRel> queryImageClassifyRelByBillTypeId(String billTypeId){
        return  imageClassifyRelMapper.queryImageClassifyRelByBillTypeId(billTypeId);
    }
}
