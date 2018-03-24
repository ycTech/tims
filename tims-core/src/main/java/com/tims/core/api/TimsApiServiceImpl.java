package com.tims.core.api;

import com.tims.core.bill.service.BillTypeService;
import com.tims.core.bill.service.TimsService;
import com.tims.core.image.service.ImageClassifyService;
import com.tims.facade.api.TimsApiService;
import com.tims.facade.domain.BillType;
import com.tims.facade.domain.ImageClassify;
import com.tims.facade.domain.vo.BillInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 *
 * @author liuzm
 * @date 2018/3/21
 */
@Component
public class TimsApiServiceImpl implements TimsApiService{

    @Autowired
    private BillTypeService billTypeService;

    @Autowired
    private ImageClassifyService imageClassifyService;

    @Autowired
    private TimsService timsService;
    @Override
    public boolean synchronizeBillType(BillType billType) throws Exception {
        if(null!=billType){
            throw  new Exception("同步信息不能为空，单据类型同步失败!");
        }
        if(billTypeService.saveBillType(billType)>0){
            return  true;
        }
        return false;
    }

    @Override
    public boolean synchronizeImageClassify(ImageClassify imageClassify) throws Exception {
        if(null!=imageClassify){
            throw  new  Exception("同步信息不能为空,图片类型同步失败!");
        }
        if(imageClassifyService.saveImageClassify(imageClassify)>0){
            return  true;
        }
        return false;
    }

    @Override
    public BillInfoVo queryBillInfoByBillNo(String billNo) throws Exception {
        BillInfoVo billInfoVo=new BillInfoVo();
        Assert.notNull(billNo,"单据id不能为空!");
        //获取单据对象

        return timsService.getBillInfoById(billNo);
    }


}
