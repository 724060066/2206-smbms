package com.buba.service.bill;

import com.buba.pojo.Bill;

import java.util.List;

public interface BillService {

    /**
     *  查询订单列表
     * @return
     */
    List<Bill> listBIll();
}
