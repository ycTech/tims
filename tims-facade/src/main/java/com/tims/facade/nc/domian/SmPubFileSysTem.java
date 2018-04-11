package com.tims.facade.nc.domian;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * Created by liuzm on 2018/4/11.
 */
public class SmPubFileSysTem  {
    @ApiModelProperty(value = "主键")
    private String pk;
    @ApiModelProperty(value = "是否是目录还是文件")
    private String isFloder;
    @ApiModelProperty(value = "path路径，也是唯一标识")
    private String path;
    @ApiModelProperty(value = "是文件就传md5")
    private String md5;
    @ApiModelProperty(value = "上传时间")
    private long time;
    @ApiModelProperty(value = "文件大小")
    private int fileLength;
    @ApiModelProperty(value = "文件URL")
    private  String url;
    @ApiModelProperty(value = "创建人")
    private String creator;
    @ApiModelProperty(value = "创建人编码")
    private String createUserCode;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "更新人")
    private String updateUser;
    @ApiModelProperty(value = "更新人编码")
    private String updateUserCode;
    @ApiModelProperty(value = "系统来源")
    private String systemId;

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

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getIsFloder() {
        return isFloder;
    }

    public void setIsFloder(String isFloder) {
        this.isFloder = isFloder;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }
}
