package com.tims.facade.nc.domian;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * yecb
 */
public class OrgInfo {
    @ApiModelProperty(value = "主键")
    private String corpId;
    @ApiModelProperty(value = "编码")
    private String unitCode;
    @ApiModelProperty(value = "名称")
    private String unitName;
    @ApiModelProperty(value = "简称")
    private String unitShortName;
    @ApiModelProperty(value = "删除标志")
    private long dr;
    @ApiModelProperty(value = "来源系统")
    private long vSystem;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitShortName() {
        return unitShortName;
    }

    public void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }

    public long getDr() {
        return dr;
    }

    public void setDr(long dr) {
        this.dr = dr;
    }

    public long getvSystem() {
        return vSystem;
    }

    public void setvSystem(long vSystem) {
        this.vSystem = vSystem;
    }
}
