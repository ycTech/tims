package com.tims.core.image.service;

import com.tims.core.image.repository.ImageInfoRepository;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageInfoService {
    @Autowired
    private ImageInfoRepository imageInfoRepository;

    /**
     * 新增图片
     * @param imageInfo
     * @return
     */
    public int saveImageInfo(ImageInfo imageInfo){
        imageInfo.setId(PkUtil.getUUID());
        imageInfo.setCreateTime(new Date());
        return imageInfoRepository.saveImageInfo(imageInfo);
    }

    /**
     * 更新图片
     * @param imageInfo
     * @return
     */
    public int updateImageInfo(ImageInfo imageInfo){
        return  imageInfoRepository.updateImageInfo(imageInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageInfo(String id){
        return  imageInfoRepository.deleteImageInfo(id);
    }
    /**
     * 根据单据编码查询图片列表
     * @param billNo
     * @return
     */
    public BillImageVo queryImagesByBillNo(String billNo) {
        return imageInfoRepository.queryImagesByBillNo(billNo);
    }

    /**
     * 根据id获取图片列表
     * @param imageClassifyId
     * @return
     */
    public List<ImageInfo> queryImageInfoByImageClassifyId(String imageClassifyId){
        return  imageInfoRepository.queryImageInfoByImageClassifyId(imageClassifyId);
    }
}
