package com.tims.core.sys.repository;


import com.github.pagehelper.Page;
import com.tims.core.sys.mapper.SysUnitInfoMapper;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:20
 */
@Repository
public class SysUnitInfoRepository {

    @Autowired
    private SysUnitInfoMapper sysUnitInfoMapper;

    public  int saveSysUnitInfo(SysUnitInfo sysUnitInfo){
        return  sysUnitInfoMapper.saveSysUnitInfo(sysUnitInfo);
    }

    public  int updateSysUnitInfo(SysUnitInfo sysUnitInfo){
        return  sysUnitInfoMapper.updateSysUnitInfo(sysUnitInfo);
    }

    public int deleteSysUnitInfoById(String id){
        return  sysUnitInfoMapper.deleteSysUnitInfoById(id);
    }

    public Page<SysUnitInfo> querySysUnitInfoPage(SysUnitInfo sysUnitInfo){return sysUnitInfoMapper.querySysUnitInfoPage(sysUnitInfo);}
}
