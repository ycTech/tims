package com.tims.core.sys.service;

import com.tims.common.util.PwdEncryptUtil;
import com.tims.core.sys.repository.SysUserInfoRepository;
import com.tims.facade.sys.SysUserInfo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:09
 */
@Service
public class SysUserInfoService {

    @Autowired
    private SysUserInfoRepository sysUserInfoRepository;

    /**
     * 新增用户
     * @param sysUserInfo
     * @return
     */
    public  Boolean saveSysUserInfo(SysUserInfo sysUserInfo){
        sysUserInfo.setCreateTime(new Date());
        sysUserInfo.setPassword(PwdEncryptUtil.encrypt(sysUserInfo.getPassword()));
        if(sysUserInfoRepository.saveSysUserInfo(sysUserInfo)>0){
            return true;
        }
        return false ;
    }

    /**
     * 更新用户
     * @param sysUserInfo
     * @return
     */
    public  Boolean updateSysUserInfo(SysUserInfo sysUserInfo){
        sysUserInfo.setUpdateTime(new Date());
        sysUserInfo.setPassword(PwdEncryptUtil.encrypt(sysUserInfo.getPassword()));
        if( sysUserInfoRepository.updateSysUserInfo(sysUserInfo)>0){
            return true;
        }
        return false;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public Boolean deleteSysUserInfoById(String id){
        if(sysUserInfoRepository.deleteSysUserInfoById(id)>0){
            return true;
        }
        return false;
    }
}
