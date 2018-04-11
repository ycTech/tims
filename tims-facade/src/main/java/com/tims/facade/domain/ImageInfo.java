package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ImageInfo implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "图片分类ID")
    private String imageClassifyId;
    @ApiModelProperty(value = "图片名称")
    private String imageName;
    @ApiModelProperty(value = "URL")
    private String url;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageClassifyId() {
        return imageClassifyId;
    }

    public void setImageClassifyId(String imageClassifyId) {
        this.imageClassifyId = imageClassifyId;
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
}
