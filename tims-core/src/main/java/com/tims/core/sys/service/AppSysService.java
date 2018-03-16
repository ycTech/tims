package com.tims.core.sys.service;

import com.tims.core.sys.repository.AppSysRepository;
import com.tims.facade.domain.AppSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppSysService {
    @Autowired
    private AppSysRepository appSysRepository;

    /**
     * 新增图片
     * @param appSys
     * @return
     */
    public int saveAppSys(AppSys appSys) {
        return appSysRepository.saveAppSys(appSys);
    }
    /**
     * 更新图片
     * @param appSys
     * @return
     */
    public int updateAppSys(AppSys appSys){
        return  appSysRepository.updateAppSys(appSys);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteAppSys(String id){
        return  appSysRepository.deleteAppSys(id);
    }


}
