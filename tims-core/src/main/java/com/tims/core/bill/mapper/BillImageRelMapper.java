package com.tims.core.bill.mapper;

import com.tims.facade.domain.BillImageRel;
import com.tims.facade.domain.BillInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillImageRelMapper {
    /**
     * 新增影像图片关系
     * @param billImageRel
     * @return
     */
    public int saveBillImageRel(BillImageRel billImageRel);

    /**
     * 更新影像图片关系
     * @param billImageRel
     * @return
     */
    public int updateBillImageRel(BillImageRel billImageRel);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteBillImageRel(String id);


}
