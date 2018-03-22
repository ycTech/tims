package com.tims.core.image.mapper;

import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.ImageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据单据编码查询图片列表
     * @param billNo
     * @return
     */
    public BillImageVo queryImagesByBillNo(@Param("billNo") String billNo);

    /**
     * 根据id获取图片列表
     * @param imageClassifyId
     * @return
     */
    public List<ImageInfo> queryImageInfoByImageClassifyId(@Param("imageClassifyId") String imageClassifyId);
}
