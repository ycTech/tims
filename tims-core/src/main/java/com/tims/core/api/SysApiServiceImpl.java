package com.tims.core.api;

import com.github.pagehelper.Page;
import com.tims.core.sys.service.SysUnitInfoService;
import com.tims.core.sys.service.SysUserInfoService;
import com.tims.facade.api.SysApiService;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:45
 */
@Service
public class SysApiServiceImpl implements SysApiService {

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private SysUnitInfoService sysUnitInfoService;


    @Override
    public Boolean saveSysUserInfo(SysUserInfo sysUserInfo) {
        return sysUserInfoService.saveSysUserInfo(sysUserInfo);
    }

    @Override
    public Boolean updateSysUserInfo(SysUserInfo sysUserInfo) {
        return sysUserInfoService.updateSysUserInfo(sysUserInfo);
    }

    @Override
    public Boolean deleteSysUserInfoById(String id) {
        return sysUserInfoService.deleteSysUserInfoById(id);
    }

    @Override
    public Boolean saveSysUnitInfo(SysUnitInfo sysUnitInfo) {
        return sysUnitInfoService.saveSysUnitInfo(sysUnitInfo);
    }

    @Override
    public Boolean updateSysUnitInfo(SysUnitInfo sysUnitInfo) {
        return sysUnitInfoService.updateSysUnitInfo(sysUnitInfo);
    }

    @Override
    public Boolean deleteSysUnitInfo(String id) {
        return sysUnitInfoService.deleteSysUnitInfo(id);
    }

    @Override
    public Page<SysUserInfo> querySysuserInfoPage(SysUserInfo sysUserInfo) {
        return sysUserInfoService.querySysuserInfoPage(sysUserInfo);
    }

    @Override
    public Page<SysUnitInfo> querySysUnitInfoPage(SysUnitInfo sysUnitInfo) {
        return sysUnitInfoService.querySysUnitInfoPage(sysUnitInfo);
    }
}
