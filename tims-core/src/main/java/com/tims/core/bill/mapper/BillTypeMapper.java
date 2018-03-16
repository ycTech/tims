package com.tims.core.bill.mapper;

import com.tims.facade.domain.BillType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillTypeMapper {

    /**
     * 新增影像类型
     * @param billType
     * @return
     */
    public int saveBillType(BillType billType);

    /**
     * 更新影像类型
     * @param billType
     * @return
     */
    public int updateBillType(BillType billType);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillType(String id);
}
