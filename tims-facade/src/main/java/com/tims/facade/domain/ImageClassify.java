package com.tims.facade.domain;

import io.swagger.annotations.ApiModelProperty;

public class ImageClassify {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "图片编码")
    private String classifyCode;
    @ApiModelProperty(value = "图片名称")
    private String classifyName;
    @ApiModelProperty(value = "状态")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
