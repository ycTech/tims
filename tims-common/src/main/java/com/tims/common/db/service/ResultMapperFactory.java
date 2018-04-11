package com.tims.common.db.service;

import com.tims.common.db.domain.Page;
import java.util.List;

/**
 * 数据结果映射器工厂
 * Created by chenp on 2017/9/12.
 */
public class ResultMapperFactory {
    public static ResultMapper create(Page page) {
        return new PageResultMapper(page);
    }

    public static ResultMapper create(List list) {
        return new ListResultMapper(list);
    }

    public static ResultMapper create(Object object) {
        return new SingleResultMapper(object);
    }
}
