package com.tims.facade.domain.vo;

import com.tims.facade.domain.BillType;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class BillInfoVo implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "单据编号")
    private String billNo;
    @ApiModelProperty(value = "金额")
    private Double amount;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "单据类型ID")
    private BillTypeVo billTypeVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BillTypeVo getBillTypeVo() {
        return billTypeVo;
    }

    public void setBillTypeVo(BillTypeVo billTypeVo) {
        this.billTypeVo = billTypeVo;
    }
}
