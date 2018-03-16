package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

public class BillImageRel {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "单据ID")
    private String billId;
    @ApiModelProperty(value = "图片ID")
    private String imageId;
    @ApiModelProperty(value = "图片次序")
    private String seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
