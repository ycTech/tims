package com.tims.core.image.service;

import com.tims.core.image.repository.ImageInfoRepository;
import com.tims.facade.domain.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return  imageInfoRepository.saveImageInfo(imageInfo);
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
}
