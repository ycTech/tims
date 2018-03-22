package com.tims.core.bill.repository;

import com.tims.core.bill.mapper.BillTypeMapper;
import com.tims.facade.domain.BillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillTypeRepository {
    @Autowired
    private BillTypeMapper billTypeMapper;

    /**
     * 新增影像类型
     * @param billType
     * @return
     */
    public int saveBillType(BillType billType){
        return  billTypeMapper.saveBillType(billType);
    }

    /**
     * 更新影像类型
     * @param billType
     * @return
     */
    public int updateBillType(BillType billType){
        return  billTypeMapper.updateBillType(billType);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillType(String id){
        return  billTypeMapper.deleteBillType(id);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public  BillType queryBillTypeById(String id){
        return billTypeMapper.queryBillTypeById(id);
    }
}
