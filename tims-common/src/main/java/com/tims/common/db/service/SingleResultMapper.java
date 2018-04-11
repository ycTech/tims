package com.tims.common.db.service;

/**
 * 单体对象结果映射器实现类
 * Created by chenp on 2017/9/12.
 */
public class SingleResultMapper<T> implements ResultMapper<T> {
    private T data;

    public SingleResultMapper(T data) {
        this.data = data;
    }

    public void assemble(RowMapper<T> rowMapper) {
        if (null == data) return;
        if (null == rowMapper) throw new NullPointerException("rowMapper is null");
        rowMapper.mapRow(data, 0);
    }
}
