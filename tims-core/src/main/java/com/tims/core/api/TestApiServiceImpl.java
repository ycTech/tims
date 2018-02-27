package com.tims.core.api;

import com.tims.core.test.service.TestService;
import com.tims.facade.api.TestApiService;
import com.tims.facade.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuzm
 * @create 2018-02-27 18:38
 **/
@Component
public class TestApiServiceImpl implements TestApiService {

    @Autowired
    private TestService testService;
    @Override
    public List<Test> queryTestList() {
        return testService.queryTestList();
    }
}
