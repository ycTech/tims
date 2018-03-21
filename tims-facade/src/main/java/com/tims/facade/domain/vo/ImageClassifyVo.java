package com.tims.facade.domain.vo;

import com.tims.facade.domain.ImageInfo;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 *
 * @author liuzm
 * @date 2018/3/21
 */
public class ImageClassifyVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "类型编码")
    private String classifyCode;
    @ApiModelProperty(value = "类型名称")
    private String classifyName;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "图片对象")
    List<ImageInfo> imageInfos;

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

    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    public void setImageInfos(List<ImageInfo> imageInfos) {
        this.imageInfos = imageInfos;
    }
}
