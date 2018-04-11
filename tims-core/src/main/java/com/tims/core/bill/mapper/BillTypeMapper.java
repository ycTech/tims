package com.tims.core.bill.mapper;

import com.tims.facade.domain.BillType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    public int updateBillType(@Param("billType") BillType billType);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillType(String id);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public  BillType queryBillTypeById(@Param("id") String id);
}
