package com.tims.common.db.service;


import com.tims.common.db.domain.Page;

/**
 * 分页类型数据映射器实现类
 * Created by chenp on 2017/9/12.
 */
public class PageResultMapper<T> implements ResultMapper<T>  {
    private Page<T> page ;

    public PageResultMapper(Page<T> page) {
        this.page = page;
    }

    public void assemble(RowMapper<T> rowMapper) {
        if (null == this.page || null == this.page.getList()) return;
        if (null == rowMapper) throw new NullPointerException("rowMapper is null");
        for (int count = 0; count < this.page.getList().size(); count++) {
            rowMapper.mapRow(this.page.getList().get(count), count);
        }
    }
}
