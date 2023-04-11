package com.buba.controller.bill;

import com.alibaba.fastjson.JSONArray;
import com.buba.pojo.Bill;
import com.buba.pojo.Provider;
import com.buba.service.bill.BillService;
import com.buba.service.bill.BillServiceImpl;
import com.buba.service.provider.ProviderService;
import com.buba.service.provider.ProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2023/4/6 14:46
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    @Qualifier("billService")
    private BillService billService;

    @Autowired
    @Qualifier("providerService")
    private ProviderService providerService;

    /**
     * 查询订单列表
     * @param model
     * @param queryProductName
     * @param queryProviderId
     * @param queryIsPayment
     * @return
     */
    @RequestMapping("/listBill")
    public String listBill(Model model, String queryProductName, String queryProviderId, String queryIsPayment) {
        // 查询订单信息列表
        List<Bill> billList = billService.listBill(queryProductName, queryProviderId, queryIsPayment);
        // 查询供应商下拉列表
        List<Provider> providerList = providerService.listProviderForSelect();

        model.addAttribute("billList", billList);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", queryProviderId);
        model.addAttribute("queryIsPayment", queryIsPayment);

        return "billlist";
    }

    /**
     * 使用ajax取得供应商下拉列表
     * @return
     */
    @RequestMapping(value = "/getproviderlist", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getproviderlist(){
        // 取得供应商下拉列表
        List<Provider> providerList = providerService.listProviderForSelect();
        return JSONArray.toJSONString(providerList);
    }

    /**
     * 添加订单
     * @param bill
     * @return
     */
    @RequestMapping("/saveBill")
    public String saveBill(Bill bill) {
        billService.saveBill(bill);
        return "redirect:/bill/listBill";
    }

    /**
     * 根据id查询订单
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/getBillByIdForUpdate")
    public String getBillByIdForUpdate(Model model, String id) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "billmodify";
    }

    /**
     * 修改订单
     * @param bill
     * @return
     */
    @RequestMapping("/updateBillById")
    public String updateBillById(Bill bill) {
        billService.updateBillById(bill);
        return "redirect:/bill/listBill";
    }

    /**
     * 查看订单
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/getBillById")
    public String getBillById(Model model, String id) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "billview";
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @RequestMapping("/deleteBillById")
    public String deleteBillById(String id) {
        billService.deleteBillById(id);
        return "redirect:/bill/listBill";
    }
}
