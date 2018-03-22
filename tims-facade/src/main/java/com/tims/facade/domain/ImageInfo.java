package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

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
    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
