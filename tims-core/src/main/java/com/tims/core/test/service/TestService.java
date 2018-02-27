package com.tims.core.test.service;

import com.tims.core.test.repository.TestRepository;
import com.tims.facade.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuzm
 * @create 2018-02-27 18:36
 **/
@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    public List<Test> queryTestList(){
        return  testRepository.queryTestList();
    }
}
