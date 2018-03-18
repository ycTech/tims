package com.tims.facade.api;

import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.ImageClassify;
import com.tims.facade.domain.ImageClassifyRel;
import com.tims.facade.domain.ImageInfo;
import com.tims.facade.hessian.HessianService;

@HessianService(uri = "/imageApiService")
public interface ImageApiService {
    /**
     * 新增图片
     * @param imageInfo
     * @return
     */
    public boolean saveImageInfo(ImageInfo imageInfo);

    /**
     * 更新图片
     * @param imageInfo
     * @return
     */
    public boolean updateImageInfo(ImageInfo imageInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    public  boolean deleteImageInfo(String id);

    /**
     * 新增图片类型
     * @param imageClassify
     * @return
     */
    public  boolean saveImageClassify(ImageClassify imageClassify);

    /**
     * 更新图片类型
     * @param imageClassify
     * @return
     */
    public  boolean updateImageClassify(ImageClassify imageClassify);

    /**
     * 删除
     * @param id
     * @return
     */
    public  boolean deleteImageClassify(String  id);

    /**
     * 新增图片关系
     * @param imageClassifyRel
     * @return
     */
    public  boolean saveImageClassifyRel(ImageClassifyRel imageClassifyRel);

    /**
     * 更新图片关系
     * @param imageClassifyRel
     * @return
     */
    public  boolean updatImageClassifyRel(ImageClassifyRel imageClassifyRel);

    /**
     * 删除
     * @param id
     * @return
     */
    public  boolean deleteImageClassifyRel(String id);

    /**
     * 根据单据ID查询图片或者文件
     * @param billId
     * @return
     */
    public BillImageVo queryImagesByBillId(String billId);

    /**
     * 根据单据编码查询图片或者文件
     * @param billNo
     * @return
     */
    public BillImageVo queryImagesByBillNo(String billNo);
}
