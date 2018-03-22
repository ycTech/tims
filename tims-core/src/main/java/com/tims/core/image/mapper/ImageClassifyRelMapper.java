package com.tims.core.image.mapper;

import com.tims.facade.domain.ImageClassifyRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageClassifyRelMapper {

    /**
     * 新增图片
     * @param imageClassifyRel
     * @return
     */
    public int saveImageClassifyRel(ImageClassifyRel imageClassifyRel);

    /**
     * 更新图片
     * @param imageClassifyRel
     * @return
     */
    public int updateImageClassifyRel(ImageClassifyRel imageClassifyRel);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageClassifyRel(String id);

    /**
     * 根据id获取图片类型列表
     * @param billTypeId
     * @return
     */
    public List<ImageClassifyRel> queryImageClassifyRelByBillTypeId(@Param("billTypeId") String billTypeId);
}
