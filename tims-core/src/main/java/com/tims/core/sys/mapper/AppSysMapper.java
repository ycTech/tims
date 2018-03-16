package com.tims.core.sys.mapper;

import com.tims.facade.domain.AppSys;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppSysMapper {
    /**
     * 新增图片
     * @param appSys
     * @return
     */
    public int saveAppSys(AppSys appSys);

    /**
     * 更新图片
     * @param appSys
     * @return
     */
    public int updateAppSys(AppSys appSys);

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteAppSys(String id);
}
