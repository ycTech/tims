package com.tims.core.test.repository;

import com.tims.core.test.mapper.TestMapper;
import com.tims.facade.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuzm
 * @create 2018-02-27 18:35
 **/
@Repository
public class TestRepository {
    @Autowired
    private TestMapper testMapper;

    public List<Test> queryTestList(){
        return  testMapper.queryTestList();
    }
}
