package com.buba.dao.bill;

import com.buba.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

public interface BillDao {

    /**
     * 根据id删除订单
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    int deleteBillById(Connection connection, String billId) throws Exception;

    /**
     * 查询订单信息列表
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     * @throws Exception
     */
    List<Bill> listBill(@Param("productName") String productName, @Param("providerId") String providerId, @Param("isPayment") String isPayment);

    /**
     * 添加订单
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    int saveBill(Connection connection, Bill bill) throws Exception;

    /**
     * 根据id查询订单信息
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    Bill getBillById(Connection connection, String billId) throws Exception;

    /**
     * 根据id修改订单信息
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    int updateBillById(Connection connection, Bill bill) throws Exception;
}
