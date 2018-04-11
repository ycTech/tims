package com.tims.facade.nc.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/4/11.
 */
public class FileInfoVo {

    @ApiModelProperty(value = "是否是文件还是目录Y/N")
    private String isFloder;
    @ApiModelProperty(value = "path路径，也是唯一标识")
    private String path;
    @ApiModelProperty(value = "是文件有md5")
    private String md5;
    @ApiModelProperty(value = "时间")
    private long time;
    @ApiModelProperty(value = "创建人Pk")
    private String creator;
    @ApiModelProperty(value = "公司PK")
    private String pkCorp;
    @ApiModelProperty(value = "文件长度")
    private int fileLength;
    @ApiModelProperty(value = "系统来源")
    private String systemId;

    public String getSystemId() {
        return systemId;
    }

    public String getPkCorp() {
        return pkCorp;
    }

    public void setPkCorp(String pkCorp) {
        this.pkCorp = pkCorp;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }
}
