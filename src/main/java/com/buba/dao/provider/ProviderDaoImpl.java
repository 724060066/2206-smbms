package com.buba.dao.provider;

import com.buba.dao.BaseDao;
import com.buba.pojo.Bill;
import com.buba.pojo.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @date 2023/3/24 09:03
 */
public class ProviderDaoImpl implements ProviderDao{
    /**
     *  查询供应商下拉列表
     * @param connection
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> listProviderForSelect(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Provider> providerList = new ArrayList<>();
        if(connection != null){
            // 声明sql
            String sql = "select id,proName from smbms_provider";

            Object[] params = {};

            // 执行sql并返回查询结果
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            // 循环结果集，并封装到students实体类
            while(rs.next()){
                // 将结果集里的数据封装到实体类对应的属性里
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProName(rs.getString("proName"));

                // 将查询结果放入list
                providerList.add(provider);
            }
            // 释放资源（注意不要释放connection）
            BaseDao.closeResource(null, pstm, rs);
        }
        // 将查询结果（list）返回给service方法
        return providerList;
    }
}
