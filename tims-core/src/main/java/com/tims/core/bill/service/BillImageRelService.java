package com.tims.core.bill.service;

import com.tims.common.util.PkUtil;
import com.tims.core.bill.mapper.BillImageRelMapper;
import com.tims.core.bill.repository.BillImageRelRepository;
import com.tims.facade.domain.BillImageRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillImageRelService {

    @Autowired
    private BillImageRelRepository billImageRelRepository;


    /**
     * 新增影像图片关系
     * @param billImageRel
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public void saveBillImageRel(BillImageRel billImageRel){
        billImageRel.setId(PkUtil.getUUID());
        billImageRelRepository.saveBillImageRel(billImageRel);
    }

    /**
     * 更新影像图片关系
     * @param billImageRel
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int updateBillImageRel(BillImageRel billImageRel){
        return billImageRelRepository.updateBillImageRel(billImageRel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int  deleteBillImageRel(String id){
        return  billImageRelRepository.deleteBillImageRel(id);
    }
}
