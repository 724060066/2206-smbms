package com.buba.service.bill;

import com.buba.dao.BaseDao;
import com.buba.dao.bill.BillDao;
import com.buba.dao.bill.BillDaoImpl;
import com.buba.pojo.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @date 2023/3/23 15:01
 */
public class BillServiceImpl implements BillService{

    private BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    /**
     * 查询订单列表
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     */
    @Override
    public List<Bill> listBIll(String productName, String providerId, String isPayment) {
        Connection connection = null;
        List<Bill> billList = null;

        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的查询方法，并传入connection
            billList = billDao.listBill(connection, productName, providerId, isPayment);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
        // 将查询结果返回给servlet
        return billList;
    }

    /**
     * 添加订单
     * @param bill
     */
    @Override
    public void saveBill(Bill bill) {
        Connection connection = null;
        int num = 0;
        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的添加方法
            billDao.saveBill(connection, bill);
            // 根据dao层的添加方法返回的num判断添加是否成功
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
    }
}
