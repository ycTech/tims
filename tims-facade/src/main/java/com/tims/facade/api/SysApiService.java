package com.tims.facade.api;

import com.github.pagehelper.Page;
import com.tims.facade.hessian.HessianService;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:32
 */
@HessianService(uri = "/sysApiService")
public interface SysApiService {


    /**
     * 新增用户
     * @param sysUserInfo
     * @return
     */
    public  Boolean saveSysUserInfo(SysUserInfo sysUserInfo);

    /**
     * 更新用户
     * @param sysUserInfo
     * @return
     */
    public  Boolean updateSysUserInfo(SysUserInfo sysUserInfo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public Boolean  deleteSysUserInfoById(String id);

    /**
     * 新增单位
     * @param sysUnitInfo
     * @return
     */
    public  Boolean saveSysUnitInfo(SysUnitInfo sysUnitInfo);

    /**
     * 更新单位
     * @param sysUnitInfo
     * @return
     */
    public  Boolean updateSysUnitInfo(SysUnitInfo sysUnitInfo);

    /**
     * 删除单位
     * @param id
     * @return
     */
    public  Boolean deleteSysUnitInfo(String id);

    /**
     * 分页用户
     * @param sysUserInfo
     * @return
     */
    public Page<SysUserInfo> querySysuserInfoPage(SysUserInfo sysUserInfo);
}
