package com.tims.core.bill.mapper;

import com.tims.facade.domain.BillInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillInfoMapper {

    public List<BillInfo>  queryBillInfo();

    /**
     * 新增影像系统
     * @param billInfo
     * @return
     */
    public int saveBillInfo(BillInfo billInfo);

    /**
     * 更新影像对象
     * @param billInfo
     * @return
     */
    public int updateBillInfo(BillInfo billInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillInfo(String id);
}
