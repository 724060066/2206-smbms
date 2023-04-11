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
 * @date 2023/3/23 15:07
 */
public class BillDaoImpl{

    /**
     * 根据id删除订单
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    public int deleteBillById(Connection connection, String billId) throws Exception {
        PreparedStatement pstm = null;
        int num = 0;
        if(connection != null){
            // 声明sql
            StringBuilder sql = new StringBuilder();
            sql.append("delete from smbms_bill where id = ? ");

            // 传入sql的数据,需要与sql中的？数量、顺序一致
            List<Object> list = new ArrayList<>();
            list.add(billId);

            System.out.println("sql --------- > " + sql);
            // 执行sql并返回查询结果
            num = BaseDao.execute(connection, pstm, sql.toString(), list.toArray());
            // 释放资源（注意不要释放connection）
            BaseDao.closeResource(null, pstm, null);
        }
        return num;
    }

    /**
     * 添加订单
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    public int saveBill(Connection connection, Bill bill) throws Exception {
        PreparedStatement pstm = null;
        int num = 0;
        if(connection != null){
            // 声明sql
            StringBuilder sql = new StringBuilder();
            sql.append("insert into smbms_bill( ");
            sql.append("billCode, ");
            sql.append("productName, ");
            sql.append("productDesc, ");
            sql.append("productUnit, ");
            sql.append("productCount, ");
            sql.append("totalPrice, ");
            sql.append("isPayment, ");
            sql.append("createdBy, ");
            sql.append("creationDate, ");
            sql.append("providerId) ");
            sql.append("values(?,?,?,?,?,?,?,?,now(),?)");

            // 传入sql的数据,需要与sql中的？数量、顺序一致
            List<Object> list = new ArrayList<>();
            list.add(bill.getBillCode());
            list.add(bill.getProductName());
            list.add(bill.getProductDesc());
            list.add(bill.getProductUnit());
            list.add(bill.getProductCount());
            list.add(bill.getTotalPrice());
            list.add(bill.getIsPayment());
            list.add(bill.getCreatedBy());
            list.add(bill.getProviderId());

            Object[] params = list.toArray();

            System.out.println("sql --------- > " + sql);
            // 执行sql并返回查询结果
            num = BaseDao.execute(connection, pstm, sql.toString(), params);
            // 释放资源（注意不要释放connection）
            BaseDao.closeResource(null, pstm, null);
        }
        return num;
    }

    /**
     * 根据id查询订单信息
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    public Bill getBillById(Connection connection, String billId) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Bill bill = new Bill();
        if(connection != null){
            // 声明sql
            StringBuffer sb = new StringBuffer();
            sb.append("select ");
            sb.append("sb.*, ");
            sb.append("sp.proName ");
            sb.append("from smbms_bill sb, ");
            sb.append("smbms_provider sp ");
            sb.append("where sb.providerId = sp.id ");
            sb.append("and sb.id = ? ");
            Object[] params = {billId};

            System.out.println("sql --------- > " + sb);
            // 执行sql并返回查询结果®
            rs = BaseDao.execute(connection, pstm, rs, sb.toString(), params);
            // 循环结果集，并封装到students实体类
            while(rs.next()){
                // 将结果集里的数据封装到实体类对应的属性里
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
                bill.setProviderName(rs.getString("proName"));
            }
            // 释放资源（注意不要释放connection）
            BaseDao.closeResource(null, pstm, rs);
        }
        // 将查询结果返回给service方法
        return bill;
    }

    /**
     * 根据id修改订单信息
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    public int updateBillById(Connection connection, Bill bill) throws Exception {
        PreparedStatement pstm = null;
        int num = 0;
        if(connection != null){
            // 声明sql
            StringBuilder sql = new StringBuilder();
            sql.append("update smbms_bill set ");
            sql.append("billCode = ?, ");
            sql.append("productName = ?, ");
            sql.append("productDesc = ?, ");
            sql.append("productUnit = ?, ");
            sql.append("productCount = ?, ");
            sql.append("totalPrice = ?, ");
            sql.append("isPayment = ?, ");
            sql.append("modifyBy = ?, ");
            sql.append("modifyDate = now(), ");
            sql.append("providerId = ? ");
            sql.append("where id = ?");

            // 传入sql的数据,需要与sql中的？数量、顺序一致
            List<Object> list = new ArrayList<>();
            list.add(bill.getBillCode());
            list.add(bill.getProductName());
            list.add(bill.getProductDesc());
            list.add(bill.getProductUnit());
            list.add(bill.getProductCount());
            list.add(bill.getTotalPrice());
            list.add(bill.getIsPayment());
            list.add(bill.getModifyBy());
            list.add(bill.getProviderId());
            list.add(bill.getId());

            Object[] params = list.toArray();

            System.out.println("sql --------- > " + sql);
            // 执行sql并返回查询结果
            num = BaseDao.execute(connection, pstm, sql.toString(), params);
            // 释放资源（注意不要释放connection）
            BaseDao.closeResource(null, pstm, null);
        }
        return num;
    }
}
