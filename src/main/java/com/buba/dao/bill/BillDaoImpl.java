package com.buba.dao.bill;

import com.buba.dao.BaseDao;
import com.buba.pojo.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2023/3/23 15:07
 */
public class BillDaoImpl implements BillDao{
    /**
     * 查询订单信息列表
     * @param connection
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> listBill(Connection connection) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<>();
        if(connection != null){
            // 声明sql
            String sql = "select * from smbms_bill ";
            List<Object> list = new ArrayList<>();

            Object[] params = list.toArray();

            System.out.println("sql --------- > " + sql);
            // 执行sql并返回查询结果®
            rs = BaseDao.execute(connection, pstm, rs, sql, params);
            // 循环结果集，并封装到students实体类
            while(rs.next()){
                // 将结果集里的数据封装到实体类对应的属性里
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getDate("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));

                // 将查询结果放入list
                billList.add(bill);
            }
            // 释放资源（注意不要释放connection）
            BaseDao.closeResource(null, pstm, rs);
        }
        // 将查询结果（list）返回给service方法
        return billList;
    }
}
