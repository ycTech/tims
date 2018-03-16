package com.tims.core.api;

import com.tims.facade.api.BillApiService;
import com.tims.facade.domain.BillImageRel;
import com.tims.facade.domain.BillInfo;
import com.tims.facade.domain.BillType;
import org.springframework.stereotype.Component;

@Component
public class BillApiServiceImpl implements BillApiService {
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
}
