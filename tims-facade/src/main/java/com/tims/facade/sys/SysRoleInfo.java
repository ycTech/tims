package com.tims.facade.sys;

import com.tims.facade.common.BaseCommonModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 角色
 * @author liuzm
 */
public class SysRoleInfo extends BaseCommonModel {

    @ApiModelProperty(value = "角色名称")
	private String roleName;
    @ApiModelProperty(value = "角色编码")
	private String roleCode;
    @ApiModelProperty(value = "系统编码")
	private String systemId;
    @ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "启用停止状态，1表示启动，0表示停止")
    private Integer status;
	@ApiModelProperty(value = "是否删除标签")
	private Boolean isDelete;

	public Boolean getDelete() {
		return isDelete;
	}

	public void setDelete(Boolean delete) {
		isDelete = delete;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getRoleName(){
		return this.roleName;
	}

	public void setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}

	public String getRoleCode(){
		return this.roleCode;
	}

	public void setSystemId(String systemId){
		this.systemId = systemId;
	}

	public String getSystemId(){
		return this.systemId;
	}




}