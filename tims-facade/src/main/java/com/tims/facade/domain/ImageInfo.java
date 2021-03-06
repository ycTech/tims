package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ImageInfo implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "图片分类ID")
    private String imageClassifyRelId;
    @ApiModelProperty(value = "单据PK")
    private String billId;
    @ApiModelProperty(value = "文件目录")
    private String filePath;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageClassifyRelId() {
        return imageClassifyRelId;
    }

    public void setImageClassifyRelId(String imageClassifyRelId) {
        this.imageClassifyRelId = imageClassifyRelId;
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
