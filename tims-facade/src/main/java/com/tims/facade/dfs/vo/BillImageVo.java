package com.tims.facade.dfs.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BillImageVo implements Serializable {
    @ApiModelProperty(value = "单据ID")
    private String billId;
    @ApiModelProperty(value = "单据编号")
    private String billNo;
    @ApiModelProperty(value = "图片名称")
    private String imageName;
    @ApiModelProperty(value = "图片URL")
    private String imageUrl;
    @ApiModelProperty(value = "图片次序")
    private String seq;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
