package com.tims.core.sys.repository;

import com.tims.core.sys.mapper.AppSysMapper;
import com.tims.facade.domain.AppSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppSysRepository {
    @Autowired
    private AppSysMapper appSysMapper;

    /**
     * 新增图片
     * @param appSys
     * @return
     */
    public int saveAppSys(AppSys appSys) {
        return appSysMapper.saveAppSys(appSys);
    }
    /**
     * 更新图片
     * @param appSys
     * @return
     */
    public int updateAppSys(AppSys appSys){
        return  appSysMapper.updateAppSys(appSys);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteAppSys(String id){
        return  appSysMapper.deleteAppSys(id);
    }



}
