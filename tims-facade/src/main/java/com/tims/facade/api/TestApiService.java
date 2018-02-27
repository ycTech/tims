package com.tims.facade.api;

import com.tims.facade.domain.Test;
import com.tims.facade.hessian.HessianService;

import java.util.List;

/**
 * @author liuzm
 * @create 2018-02-27 18:37
 **/
@HessianService(uri = "/testApiService")
public interface TestApiService {
    public List<Test> queryTestList();
}
