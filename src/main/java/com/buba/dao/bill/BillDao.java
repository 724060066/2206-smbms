package com.buba.dao.bill;

import com.buba.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public interface BillDao {

    /**
     * 查询订单信息列表
     * @param connection
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     * @throws Exception
     */
    List<Bill> listBill(Connection connection, String productName, String providerId, String isPayment)throws Exception;

    /**
     * 添加订单
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    int saveBill(Connection connection, Bill bill) throws Exception;
}
