package com.tims.core.sys.repository;

import com.github.pagehelper.Page;
import com.tims.core.sys.mapper.SysUserInfoMapper;
import com.tims.facade.sys.SysUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:06
 */
@Repository
public class SysUserInfoRepository {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    public  int saveSysUserInfo(SysUserInfo sysUserInfo){
        return  sysUserInfoMapper.saveSysUserInfo(sysUserInfo);
    }

    public  int updateSysUserInfo(SysUserInfo sysUserInfo){
        return  sysUserInfoMapper.updateSysUserInfo(sysUserInfo);
    }

    public int deleteSysUserInfoById(String id){
        return sysUserInfoMapper.deleteSysUserInfoById(id);
    }

    public Page<SysUserInfo> querySysuserInfoPage(SysUserInfo sysUserInfo){return sysUserInfoMapper.querySysuserInfoPage(sysUserInfo);}
}
