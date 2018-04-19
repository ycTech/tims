package com.tims.facade.sys;

import com.tims.facade.common.BaseModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuzm
 * @Description:单位
 * @date 2018/4/1816:50
 */
public class SysUnitInfo extends BaseModel {

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "单位简称")
    private String unitShortName;

    @ApiModelProperty(value = "单位删除标志")
    private int dr;

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
