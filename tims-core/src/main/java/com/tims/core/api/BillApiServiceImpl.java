package com.tims.core.api;

import com.github.pagehelper.Page;
import com.tims.common.util.PkUtil;
import com.tims.core.bill.service.BillImageRelService;
import com.tims.core.bill.service.BillInfoService;
import com.tims.core.image.service.ImageInfoService;
import com.tims.facade.api.BillApiService;
import com.tims.facade.bill.qo.FileStoreQo;
import com.tims.facade.bill.vo.FileStoreVo;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.BillImageRel;
import com.tims.facade.domain.BillInfo;
import com.tims.facade.domain.BillType;
import com.tims.facade.domain.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class BillApiServiceImpl implements BillApiService {

    @Autowired
    private BillInfoService billInfoService;
    @Autowired
    private BillImageRelService billImageRelService;
    @Autowired
    private ImageInfoService imageInfoService;

    @Override
    public boolean saveBillInfo(BillInfo billInfo) {
        return false;
    }

    @Override
    public boolean updateBillInfo(BillInfo billInfo) {
        return false;
    }

    @Override
    public boolean deleteBillInfo(String id) {
        return false;
    }

    @Override
    public boolean saveBillImageRel(BillImageRel billImageRel) {
        return false;
    }

    @Override
    public boolean updateBillImageRel(BillImageRel billImageRel) {
        return false;
    }

    @Override
    public boolean deleteBillImageRel(String id) {
        return false;
    }

    @Override
    public boolean saveBillType(BillType billType) {
        return false;
    }

    @Override
    public boolean updateBillType(BillType billType) {
        return false;
    }

    @Override
    public boolean deleteBillType(String id) {
        return false;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveBillImage(UploadQo uploadQos) {
        BillInfo billInfo=new BillInfo();
        billInfo.setBillNo(uploadQos.getBillNo());
//        billInfo.setBillTypeId(uploadQos.getBillTypeId());
        billInfoService.saveBillInfo(billInfo);
        ImageInfo imageInfo=new ImageInfo();
//        imageInfo.setImageClassifyId(uploadQos.getClassifyId());
        imageInfo.setImageName(uploadQos.getImageName());
        imageInfo.setUrl(uploadQos.getImageUrl());
        imageInfoService.saveImageInfo(imageInfo);
        BillImageRel billImageRel=new BillImageRel();
        billImageRel.setBillId(billInfo.getId());
        billImageRel.setImageId(imageInfo.getId());
        billImageRelService.saveBillImageRel(billImageRel);
    }

    @Override
    public Page<FileStoreVo> queryBillList(FileStoreQo fileStoreQo) {
        return billInfoService.queryBillList(fileStoreQo);
    }
}
