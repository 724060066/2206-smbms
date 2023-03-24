package com.buba.service.provider;

import com.buba.dao.BaseDao;
import com.buba.dao.provider.ProviderDao;
import com.buba.dao.provider.ProviderDaoImpl;
import com.buba.pojo.Bill;
import com.buba.pojo.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @date 2023/3/24 09:00
 */
public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao;

    public ProviderServiceImpl() {
        providerDao = new ProviderDaoImpl();
    }

    /**
     * 查询供应商下拉列表
     * @return
     */
    @Override
    public List<Provider> listProviderForSelect() {
        Connection connection = null;
        List<Provider> providerList = null;

        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的查询方法，并传入connection
            providerList = providerDao.listProviderForSelect(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
        // 将查询结果返回给servlet
        return providerList;
    }
}
