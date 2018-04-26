package com.tims.core.bill.mapper;

import com.github.pagehelper.Page;
import com.tims.facade.bill.qo.FileStoreQo;
import com.tims.facade.bill.vo.FileStoreVo;
import com.tims.facade.domain.BillInfo;
import com.tims.facade.domain.FileStore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BillInfoMapper {

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

    public Page<FileStoreVo> queryBillList(@Param("fileStoreQo")FileStoreQo fileStoreQo);
}
