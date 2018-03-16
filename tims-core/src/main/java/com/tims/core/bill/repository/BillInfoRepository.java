package com.tims.core.bill.repository;

import com.tims.core.bill.mapper.BillInfoMapper;
import com.tims.facade.domain.BillInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillInfoRepository {
    @Autowired
    private BillInfoMapper billInfoMapper;


    /**
     * 新增影像系统
     * @param billInfo
     * @return
     */
    public int saveBillInfo(BillInfo billInfo){
        return  billInfoMapper.saveBillInfo(billInfo);
    }

    /**
     * 更新影像对象
     * @param billInfo
     * @return
     */
    public int updateBillInfo(BillInfo billInfo){
        return billInfoMapper.updateBillInfo(billInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillInfo(String id){
        return  billInfoMapper.deleteBillInfo(id);
    }
}
