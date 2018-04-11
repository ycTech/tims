package com.tims.common.db.service;

/**
 * 数据结果映射器接口类
 * Created by chenp on 2017/9/12.
 */
public interface ResultMapper<T> {
    public void assemble(RowMapper<T> rowMapper);
}
