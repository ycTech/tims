package com.tims.core.bill.service;

import com.github.pagehelper.Page;
import com.tims.common.util.PkUtil;
import com.tims.core.bill.repository.BillInfoRepository;
import com.tims.facade.bill.qo.FileStoreQo;
import com.tims.facade.bill.vo.FileStoreVo;
import com.tims.facade.domain.BillInfo;
import com.tims.facade.domain.vo.BillInfoVo;
import com.tims.facade.enums.StatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public void saveBillInfo(BillInfo billInfo){
        billInfo.setId(PkUtil.getUUID());
        billInfo.setBillDate(new Date());
        billInfo.setStatus(StatusEnum.VALID.name());
        billInfoRepository.saveBillInfo(billInfo);
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


    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public  BillInfo queryBillInfoById(String  id){
        return  billInfoRepository.queryBillInfoById(id);
    }

    /**
     * 查询单据列表
     * @param fileStoreQo
     */
    public Page<FileStoreVo> queryBillList(FileStoreQo fileStoreQo){
        return billInfoRepository.queryBillList(fileStoreQo);
    }

}
