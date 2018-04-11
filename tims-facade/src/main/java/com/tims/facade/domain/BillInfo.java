package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class BillInfo implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "单据日期")
    private Date billDate;
    @ApiModelProperty(value = "机构ID")
    private String orgId;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "单据类型ID")
    private String billTypeId;
    @ApiModelProperty(value = "单据编号")
    private String billNo;
    @ApiModelProperty(value = "金额")
    private Double amount;
    @ApiModelProperty(value = "任务类型")
    private Double taskType;
    @ApiModelProperty(value = "状态")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(String billTypeId) {
        this.billTypeId = billTypeId;
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

    public Double getTaskType() {
        return taskType;
    }

    public void setTaskType(Double taskType) {
        this.taskType = taskType;
    }
}

