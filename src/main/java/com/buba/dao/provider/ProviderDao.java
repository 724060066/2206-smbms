package com.buba.dao.provider;

import com.buba.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {

    /**
     *  查询供应商下拉列表
     * @param connection
     * @return
     * @throws Exception
     */
    List<Provider> listProviderForSelect(Connection connection)throws Exception;
}
