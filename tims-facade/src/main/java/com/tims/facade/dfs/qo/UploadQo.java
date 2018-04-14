package com.tims.facade.dfs.qo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UploadQo implements Serializable {

    @ApiModelProperty(value = "单据日期",hidden = false)
    private Date billDate;
    @ApiModelProperty(value = "制单人")
    private String userCode;
    @ApiModelProperty(value = "单据类型")
    private String billType;
    @ApiModelProperty(value = "单据编号")
    private String billNo;
    @ApiModelProperty(value = "单据ID")
    private String billId;
    @ApiModelProperty(value = "路径")
    private String path;
    @ApiModelProperty(value = "是否目录")
    private String isFolder;
    @ApiModelProperty(value = "操作类型(add(新增),delete（删除）)")
    private String operate;
    @ApiModelProperty(value = "图片名称")
    private String imageName;
    @ApiModelProperty(value = "文件大小")
    private String fileSize;
    @ApiModelProperty(value = "图片URL")
    private String imageUrl;
    @ApiModelProperty(value = "base64")
    private String imageBase64;

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}

