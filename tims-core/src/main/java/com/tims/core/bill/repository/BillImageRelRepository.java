package com.tims.core.bill.repository;

import com.tims.core.bill.mapper.BillImageRelMapper;
import com.tims.facade.domain.BillImageRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillImageRelRepository {
    @Autowired
    private BillImageRelMapper billImageRelMapper;
    /**
     * 新增影像图片关系
     * @param billImageRel
     * @return
     */
    public void saveBillImageRel(BillImageRel billImageRel){
        billImageRelMapper.saveBillImageRel(billImageRel);
    }

    /**
     * 更新影像图片关系
     * @param billImageRel
     * @return
     */
    public int updateBillImageRel(BillImageRel billImageRel){
        return billImageRelMapper.updateBillImageRel(billImageRel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillImageRel(String id){
        return  billImageRelMapper.deleteBillImageRel(id);
    }
}
