package com.tims.facade.sys;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liuzm on 2018/4/11.
 */
public class SysUserInfo {

    @ApiModelProperty(value = "用户主键")
    private String cuserid;
    @ApiModelProperty(value = "用户编码")
    private String  userCode;
    @ApiModelProperty(value = "用户名字")
    private String userName;
    @ApiModelProperty(value = "用户所属公司")
    private String pkCorp;
    @ApiModelProperty(value = "删除标志")
    private int dr;

    public String getCuserid() {
        return cuserid;
    }

    public void setCuserid(String cuserid) {
        this.cuserid = cuserid;
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

    public String getPkCorp() {
        return pkCorp;
    }

    public void setPkCorp(String pkCorp) {
        this.pkCorp = pkCorp;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
