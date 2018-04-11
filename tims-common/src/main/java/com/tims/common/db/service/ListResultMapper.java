package com.tims.common.db.service;

import java.util.List;

/**
 * 列表类型数据映射器实现类
 * Created by chenp on 2017/9/11.
 */
public class ListResultMapper<T> implements ResultMapper<T> {
    private List<T> datas;

    public ListResultMapper(List<T> datas) {
        this.datas = datas;
    }

    public void assemble(RowMapper<T> rowMapper) {
        if (null == datas) return;
        if (null == rowMapper) throw new NullPointerException("rowMapper is null");
        for (int count = 0; count < datas.size(); count++) {
            rowMapper.mapRow(datas.get(count), count);
        }
    }
}
