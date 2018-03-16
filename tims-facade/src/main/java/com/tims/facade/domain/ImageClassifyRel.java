package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

public class ImageClassifyRel {
    @ApiModelProperty(value = "主键")
    private  String id;
    @ApiModelProperty(value = "机构ID")
    private String orgId;
    @ApiModelProperty(value = "系统ID")
    private String sysId;
    @ApiModelProperty(value = "单据类型ID")
    private String billTypeId;
    @ApiModelProperty(value = "分类ID")
    private String classifyId;
    @ApiModelProperty(value = "图片ID")
    private String Id;
    @ApiModelProperty(value = "排序")
    private String seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(String billTypeId) {
        this.billTypeId = billTypeId;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

}
