package com.tims.common.db.service;

/**
 * 自定义行对象装配接口
 * Created by chenp on 2017/9/12.
 */
public interface RowMapper<T> {
    public void mapRow(T rowObj, int index);
}
