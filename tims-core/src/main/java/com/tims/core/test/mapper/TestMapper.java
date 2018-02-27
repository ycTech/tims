package com.tims.core.test.mapper;

import com.tims.facade.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuzm
 * @create 2018-02-27 18:28
 **/
@Mapper
public interface TestMapper {
    /**
     * 测试用例
     * @return
     */
    public List<Test> queryTestList();
    
}
