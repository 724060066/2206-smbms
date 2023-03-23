package com.buba.dao.bill;

import com.buba.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public interface BillDao {

    /**
     * 查询订单信息列表
     * @param connection
     * @return
     * @throws Exception
     */
    List<Bill> listBill(Connection connection)throws Exception;
}
