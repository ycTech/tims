package com.tims.facade.sys;

import com.tims.facade.common.BaseCommonModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 权限
 * @author liuzm
 */
public class SysAuthorityInfo extends BaseCommonModel {

    @ApiModelProperty(value = "上级id")
	private Integer parentId;
    @ApiModelProperty(value = "是否是叶子节点")
	private Boolean isLeaf;
    @ApiModelProperty(value = "路径")
	private String path;
    @ApiModelProperty(value = "name")
	private String name;
    @ApiModelProperty(value = "icon")
	private String iconCls;
    @ApiModelProperty(value = "是否下拉")
	private Boolean noDropdown;
    @ApiModelProperty(value = "是否可见")
	private Boolean visible;
    @ApiModelProperty(value = "是否可操作")
	private Boolean operable;
    @ApiModelProperty(value = "显示名称")
	private String label;
    @ApiModelProperty(value = "系统system_id")
	private String systemId;
    @ApiModelProperty(value = "对应url")
	private String url;
    @ApiModelProperty(value = "请求方法")
	private String method;
    @ApiModelProperty(value = "权限类型")
	private String authorityType;
	@ApiModelProperty(value = "是否删除")
    private  Boolean status;
	@ApiModelProperty(value = "重定向")
	private String redirect;
	@ApiModelProperty(value = "是否隐藏菜单")
	private Boolean hidden;
	@ApiModelProperty(value = "前端组件")
	private String componentPath;
	@ApiModelProperty(value = "排序")
	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getComponentPath() {
		return componentPath;
	}

	public void setComponentPath(String componentPath) {
		this.componentPath = componentPath;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getLeaf() {
		return isLeaf;
	}

	public void setLeaf(Boolean leaf) {
		isLeaf = leaf;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getNoDropdown() {
		return noDropdown;
	}

	public void setNoDropdown(Boolean noDropdown) {
		this.noDropdown = noDropdown;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getOperable() {
		return operable;
	}

	public void setOperable(Boolean operable) {
		this.operable = operable;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}


}