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

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    /**
     * 根据id删除订单
     * @param billId
     * @return true:删除成功；false：删除失败；notexist：订单不存在
     */
    @Override
    public String deleteBillById(String billId) {
        Connection connection = null;
        String delResult = "";
        int num = 0;
        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的添加方法
            num = billDao.deleteBillById(connection, billId);
            if (num == 1) {
                // 删除成功
                delResult = "true";
            } else {
                // 订单不存在
                delResult = "notexist";
            }
            // 根据dao层的添加方法返回的num判断添加是否成功
        } catch (Exception e) {
            // 删除失败
            delResult = "false";
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
        return delResult;
    }

    /**
     * 查询订单列表
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     */
    @Override
    public List<Bill> listBill(String productName, String providerId, String isPayment) {
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

    /**
     * 根据id查询订单信息
     * @param billId
     * @return
     */
    @Override
    public Bill getBillById(String billId) {
        Connection connection = null;
        Bill bill = null;
        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的查询方法，并传入connection
            bill = billDao.getBillById(connection, billId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
        // 将查询结果返回给servlet
        return bill;
    }

    /**
     * 根据id修改订单信息
     * @param bill
     */
    @Override
    public void updateBillById(Bill bill) {
        Connection connection = null;
        int num = 0;
        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的添加方法
            billDao.updateBillById(connection, bill);
            // 根据dao层的添加方法返回的num判断添加是否成功
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
    }
}
