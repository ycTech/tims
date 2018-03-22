package com.tims.core.bill.service;

import com.tims.core.bill.repository.BillTypeRepository;
import com.tims.facade.domain.BillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillTypeService {

    @Autowired
    private BillTypeRepository billTypeRepository;

    /**
     * 新增影像类型
     * @param billType
     * @return
     */
    public int saveBillType(BillType billType){
        return  billTypeRepository.saveBillType(billType);
    }

    /**
     * 更新影像类型
     * @param billType
     * @return
     */
    public int updateBillType(BillType billType){
        return  billTypeRepository.updateBillType(billType);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillType(String id){
        return  billTypeRepository.deleteBillType(id);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public  BillType queryBillTypeById(String id){
        return billTypeRepository.queryBillTypeById(id);
    }
}
