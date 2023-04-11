package com.buba.dao.provider;

import com.buba.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {

    /**
     *  查询供应商下拉列表
     * @return
     */
    List<Provider> listProviderForSelect();
}
