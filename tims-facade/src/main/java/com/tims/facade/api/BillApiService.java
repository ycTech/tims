package com.tims.facade.api;

import com.github.pagehelper.Page;
import com.tims.facade.bill.qo.FileStoreQo;
import com.tims.facade.bill.vo.FileStoreVo;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.BillImageRel;
import com.tims.facade.domain.BillInfo;
import com.tims.facade.domain.BillType;
import com.tims.facade.hessian.HessianService;

import java.util.List;

@HessianService(uri = "/billApiService")
public interface BillApiService {

    /**
     * 新增单据
     * @param billInfo
     * @return
     */
    public boolean saveBillInfo(BillInfo billInfo);

    /**
     * 更新单据
     * @param billInfo
     * @return
     */
    public boolean updateBillInfo(BillInfo billInfo);

    /**
     * 删除单据
     * @param id
     * @return
     */
    public  boolean deleteBillInfo(String id);

    /**
     * 新增单据图片关系
     * @param billImageRel
     * @return
     */
    public boolean saveBillImageRel(BillImageRel billImageRel);

    /**
     * 更新单据图片关系
     * @param billImageRel
     * @return
     */
    public boolean updateBillImageRel(BillImageRel billImageRel);

    /**
     * 删除单据图片关系
     * @param id
     * @return
     */
    public  boolean deleteBillImageRel(String id);

    /**
     * 新增单据类型
     * @param billType
     * @return
     */
    public boolean saveBillType(BillType billType);


    /**
     * 更新单据类型
     * @param billType
     * @return
     */
    public boolean updateBillType(BillType billType);

    /**
     * 删除单据类型
     * @param id
     * @return
     */
    public boolean deleteBillType(String id);

    /**
     * 新增单据图片
     * @param uploadQos
     * @return
     */
    public void saveBillImage(UploadQo uploadQos);

    /**
     * 查询单据
     * @param fileStoreQo
     * @return
     */
    public Page<FileStoreVo> queryBillList(FileStoreQo fileStoreQo);

}
