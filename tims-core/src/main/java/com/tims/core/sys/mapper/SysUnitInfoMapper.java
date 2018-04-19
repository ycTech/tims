package com.tims.core.sys.mapper;

import com.tims.facade.sys.SysUnitInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:13
 */
@Mapper
public interface SysUnitInfoMapper {

    /**
     * 新增单位
     * @param sysUnitInfo
     * @return
     */
    public  int saveSysUnitInfo(@Param("sysUnitInfo") SysUnitInfo sysUnitInfo);

    /**
     * 更新单位
     * @param sysUnitInfo
     * @return
     */
    public  int updateSysUnitInfo(@Param("sysUnitInfo")SysUnitInfo sysUnitInfo);

    /**
     * 删除单位
     * @param id
     * @return
     */
    public int deleteSysUnitInfoById(@Param("id") String id);
}
