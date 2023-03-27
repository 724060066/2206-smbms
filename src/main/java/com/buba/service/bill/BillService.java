package com.buba.service.bill;

import com.buba.pojo.Bill;

import java.util.List;

public interface BillService {

    /**
     * 查询订单列表
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     */
    List<Bill> listBIll(String productName, String providerId, String isPayment);

    /**
     * 添加订单
     * @param bill
     */
    void saveBill(Bill bill);

    /**
     * 根据id查询订单信息
     * @param billId
     * @return
     */
    Bill getBillById(String billId);

    /**
     * 根据id修改订单信息
     * @param bill
     */
    void updateBillById(Bill bill);
}
