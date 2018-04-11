package com.tims.facade.api;

import com.tims.facade.domain.BillType;
import com.tims.facade.domain.ImageClassify;
import com.tims.facade.domain.vo.BillInfoVo;
import com.tims.facade.hessian.HessianService;

@HessianService(uri = "/timsApiService")
public interface TimsApiService {
    /**
     * 同步单据类型
     * @param billType
     * @return
     * @throws Exception
     */
    public boolean synchronizeBillType(BillType billType) throws Exception;

    /**
     * 同步更新单据类型
     * @param billType
     * @return
     * @throws Exception
     */
    public boolean synchronizeUpdateBillType(BillType billType) throws  Exception;

    /**
     * 同步删除单据类型
     * @param id
     * @return
     * @throws Exception
     */
    public  boolean syschronizeDeleteBillType(String id) throws  Exception;

    /**
     * 同步图片类型
     * @param imageClassify
     * @return
     * @throws Exception
     */
    public boolean synchronizeImageClassify(ImageClassify imageClassify) throws Exception;

    /**
     * 同步更新图片类型
     * @param imageClassify
     * @return
     * @throws Exception
     */
    public  boolean synchronizeUpdateImageClassify(ImageClassify imageClassify)  throws  Exception;

    /**
     * 同步更新删除图片类型
     * @param id
     * @return
     * @throws Exception
     */
    public  boolean syschronizeDeleteImageClassify(String id)throws  Exception;

    /**
     * 根据单据id查单据情况
     * @param billNo
     * @return
     * @throws Exception
     */
    public BillInfoVo queryBillInfoByBillNo(String billNo) throws Exception;
}
