package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BillType{
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "单据类型编号")
    private String billTypeCode;
    @ApiModelProperty(value = "单据类型名称")
    private String billTypeName;
    @ApiModelProperty(value = "父单据类型编号")
    private String parentBillTypeCode;
    @ApiModelProperty(value = "是否票据类型")
    private String isTicket;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "更新人")
    private String  updateUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public String getParentBillTypeCode() {
        return parentBillTypeCode;
    }

    public void setParentBillTypeCode(String parentBillTypeCode) {
        this.parentBillTypeCode = parentBillTypeCode;
    }

    public String getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(String isTicket) {
        this.isTicket = isTicket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
