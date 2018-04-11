package com.tims.facade.sys;

import com.tims.facade.common.BaseCommonModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 角色权限
 * @author liuzm
 */
public class SysRoleAuthority extends BaseCommonModel {

    @ApiModelProperty(value = "")
	private Integer authorityId;
    @ApiModelProperty(value = "")
	private Integer roleId;
    @ApiModelProperty(value = "")
	private Integer available;

	public void setAuthorityId(Integer authorityId){
		this.authorityId = authorityId;
	}

	public Integer getAuthorityId(){
		return this.authorityId;
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