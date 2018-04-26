package com.tims.facade.bill.vo;

import com.tims.facade.page.AbstractPageForm;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class FileStoreVo extends AbstractPageForm {

    @ApiModelProperty(value = "单据PK")
    private String billId;
    @ApiModelProperty(value = "单据编码")
    private String billNo;
    @ApiModelProperty(value = "文件个数")
    private String fileNum;
    @ApiModelProperty(value = "制单人编码")
    private String userCode;
    @ApiModelProperty(value = "制单人名称")
    private String userName;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
