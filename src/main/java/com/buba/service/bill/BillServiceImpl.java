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
 * @description: TODO
 * @date 2023/3/23 15:01
 */
public class BillServiceImpl implements BillService{

    private BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    /**
     *  查询订单列表
     * @return
     */
    @Override
    public List<Bill> listBIll() {
        // TODO Auto-generated method stub
        Connection connection = null;
        List<Bill> billList = null;

        try {
            // 创建connection
            connection = BaseDao.getConnection();
            // 调用dao层的查询方法，并传入connection
            billList = billDao.listBill(connection);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            // 关闭connection
            BaseDao.closeResource(connection, null, null);
        }
        // 将查询结果返回给servlet
        return billList;
    }
}
