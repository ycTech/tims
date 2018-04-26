package com.tims.facade.bill.qo;

import com.tims.facade.page.AbstractPageForm;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class FileStoreQo extends AbstractPageForm {
    @ApiModelProperty(value = "单据ID")
    private String billId;
    @ApiModelProperty(value = "单据编码")
    private String billNo;
    @ApiModelProperty(value = "单据类型")
    private String billType;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
