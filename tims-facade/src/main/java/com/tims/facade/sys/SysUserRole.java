package com.tims.facade.sys;

import com.tims.facade.common.BaseCommonModel;
import io.swagger.annotations.ApiModelProperty;


public class SysUserRole extends BaseCommonModel {

    @ApiModelProperty(value = "")
	private Integer userId;
    @ApiModelProperty(value = "")
	private Integer roleId;
    @ApiModelProperty(value = "")
	private Integer available;

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}

	public Integer getRoleId(){
		return this.roleId;
	}

	public void setAvailable(Integer available){
		this.available = available;
	}

	public Integer getAvailable(){
		return this.available;
	}

}