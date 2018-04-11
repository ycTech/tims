package com.tims.facade.dfs.qo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UploadQo implements Serializable {

    @ApiModelProperty(value = "单据日期",hidden = false)
    private Date billDate;
    @ApiModelProperty(value = "单据类型ID")
    private String billTypeId;
    @ApiModelProperty(value = "附件分类ID")
    private String classifyId;
    @ApiModelProperty(value = "附件分类名称")
    private String classifyName;
    @ApiModelProperty(value = "单据编号")
    private String billNo;
    @ApiModelProperty(value = "图片名称")
    private String imageName;
    @ApiModelProperty(value = "图片URL")
    private String imageUrl;
    @ApiModelProperty(value = "base64")
    private String imageBase64;
    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
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

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}

