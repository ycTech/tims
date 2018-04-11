package com.tims.core.bill.mapper;

import com.tims.facade.domain.BillInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BillInfoMapper {

    public List<BillInfo>  queryBillInfo();

    /**
     * 新增影像系统
     * @param billInfo
     * @return
     */
    public void saveBillInfo(BillInfo billInfo);

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

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public  BillInfo queryBillInfoById(@Param("id") String  id);
}
