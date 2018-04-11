package com.tims.facade.dict;

import com.tims.facade.common.BaseCommonModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/4/11.
 */
public class Company extends BaseCommonModel {

    @ApiModelProperty(value = "公司主键")
    private String pkCorp;
    @ApiModelProperty(value = "公司编码")
    private String unitCode;
    @ApiModelProperty(value = "公司名称")
    private String unitName;
    @ApiModelProperty(value = "公司简称")
    private String unitShortName;
    @ApiModelProperty(value = "删除标志")
    private int dr;


    public String getPkCorp() {
        return pkCorp;
    }

    public void setPkCorp(String pkCorp) {
        this.pkCorp = pkCorp;
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

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
