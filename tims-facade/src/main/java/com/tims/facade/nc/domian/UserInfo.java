package com.tims.facade.nc.domian;

import io.swagger.annotations.ApiModelProperty;

/**
 * yecb
 */
public class UserInfo {
    @ApiModelProperty(value = "主键")
    private String userId;
    @ApiModelProperty(value = "编码")
    private String userCode;
    @ApiModelProperty(value = "名称")
    private String userName;
    @ApiModelProperty(value = "公司ID")
    private String corpId;
    @ApiModelProperty(value = "删除标志")
    private long dr;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public long getDr() {
        return dr;
    }

    public void setDr(long dr) {
        this.dr = dr;
    }
}
