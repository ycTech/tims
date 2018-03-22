package com.tims.facade.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Administrator
 * @date 2018/3/21
 */
public class BillTypeVo implements Serializable {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "单据类型编号")
    private String billTypeCode;
    @ApiModelProperty(value = "单据类型名称")
    private String billTypeName;
    @ApiModelProperty(value = "父单据类型编号")
    private String parentBillTypeCode;
    @ApiModelProperty(value = "是否票据类型")
    private String isTicket;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "图片类型")
    List<ImageClassifyVo> imageClassifyVos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillTypeCode() {
        return billTypeCode;
    }

    public void setBillTypeCode(String billTypeCode) {
        this.billTypeCode = billTypeCode;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public String getParentBillTypeCode() {
        return parentBillTypeCode;
    }

    public void setParentBillTypeCode(String parentBillTypeCode) {
        this.parentBillTypeCode = parentBillTypeCode;
    }

    public String getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(String isTicket) {
        this.isTicket = isTicket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ImageClassifyVo> getImageClassifyVos() {
        return imageClassifyVos;
    }

    public void setImageClassifyVos(List<ImageClassifyVo> imageClassifyVos) {
        this.imageClassifyVos = imageClassifyVos;
    }
}
