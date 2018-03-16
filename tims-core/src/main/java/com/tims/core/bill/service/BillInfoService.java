package com.tims.core.bill.service;

import com.tims.core.bill.repository.BillInfoRepository;
import com.tims.facade.domain.BillInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillInfoService {
    @Autowired
    private BillInfoRepository billInfoRepository;

    /**
     * 新增影像系统
     * @param billInfo
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int saveBillInfo(BillInfo billInfo){
        return  billInfoRepository.saveBillInfo(billInfo);
    }

    /**
     * 更新影像对象
     * @param billInfo
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int updateBillInfo(BillInfo billInfo){
        return billInfoRepository.updateBillInfo(billInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int  deleteBillInfo(String id){
        return  billInfoRepository.deleteBillInfo(id);
    }
}
