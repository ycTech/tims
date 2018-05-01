package com.tims.core.sys.mapper;

import com.github.pagehelper.Page;
import com.tims.facade.sys.SysUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1816:53
 */
@Mapper
public interface SysUserInfoMapper {


    /**
     * 分页查询用户
     * @param sysUserInfo
     * @return
     */
    public Page<SysUserInfo> querySysuserInfoPage(@Param("sysUserInfo") SysUserInfo sysUserInfo);
    /**
     * 新增用户
     * @param sysUserInfo
     * @return
     */
    public  int saveSysUserInfo(@Param("sysUserInfo") SysUserInfo sysUserInfo);

    /**
     *更新用户
     * @param sysUserInfo
     * @return
     */
    public  int updateSysUserInfo(@Param("sysUserInfo") SysUserInfo sysUserInfo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public  int deleteSysUserInfoById(@Param("id") String id);
}
