package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class FileStore implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "单据PK")
    private String billId;
    @ApiModelProperty(value = "单据编码")
    private String billNo;
    @ApiModelProperty(value = "单据类型")
    private String billType;
    @ApiModelProperty(value = "文件目录")
    private String filePath;
    @ApiModelProperty(value = "制单人")
    private String userCode;
    @ApiModelProperty(value = "文件大小")
    private String fileSize;
    @ApiModelProperty(value = "图片名称")
    private String imageName;
    @ApiModelProperty(value = "URL")
    private String url;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "删除标识，默认是0")
    private String isDelete;
    @ApiModelProperty(value = "是否目录")
    private String isFolder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
