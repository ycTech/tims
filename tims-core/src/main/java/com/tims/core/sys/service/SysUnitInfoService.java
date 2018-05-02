package com.tims.core.sys.service;


import com.github.pagehelper.Page;
import com.tims.core.sys.repository.SysUnitInfoRepository;
import com.tims.facade.sys.SysUnitInfo;
import java.util.Date;

import com.tims.facade.sys.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:22
 */
@Service
public class SysUnitInfoService {

    @Autowired
    private SysUnitInfoRepository sysUnitInfoRepository;

    /**
     * 新增单位
     * @param sysUnitInfo
     * @return
     */
    public  Boolean saveSysUnitInfo(SysUnitInfo sysUnitInfo){
        sysUnitInfo.setCreateTime(new Date());
        if(sysUnitInfoRepository.saveSysUnitInfo(sysUnitInfo)>0){
            return true;
        }
        return false;
    }


    /**
     * 更新单位
     * @param sysUnitInfo
     * @return
     */
    public Boolean updateSysUnitInfo(SysUnitInfo sysUnitInfo){
        sysUnitInfo.setUpdateTime(new Date());
        if(sysUnitInfoRepository.updateSysUnitInfo(sysUnitInfo)>0){
            return true;
        }
        return false;
    }


    /**
     * 删除单位
     * @param id
     * @return
     */
    public  Boolean deleteSysUnitInfo(String id){
        if(sysUnitInfoRepository.deleteSysUnitInfoById(id)>0){
            return true;
        }
        return false;
    }

    /**
     * 单位信息分页
     * @param sysUnitInfo
     * @return
     */
    public Page<SysUnitInfo> querySysUnitInfoPage(SysUnitInfo sysUnitInfo){
        return sysUnitInfoRepository.querySysUnitInfoPage(sysUnitInfo);
    }
}
