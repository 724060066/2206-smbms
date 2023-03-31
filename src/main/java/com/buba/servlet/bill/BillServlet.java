package com.buba.servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.buba.pojo.Bill;
import com.buba.pojo.Provider;
import com.buba.pojo.User;
import com.buba.service.bill.BillService;
import com.buba.service.bill.BillServiceImpl;
import com.buba.service.provider.ProviderService;
import com.buba.service.provider.ProviderServiceImpl;
import com.buba.tool.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理servlet111
 * @author chenrui
 * @version 1.0
 * @date 2023/3/23 14:56
 */
public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method != null) {
            switch (method) {
                case "query":
                    // 查询订单列表
                    this.listBill(req, resp);
                    break;
                case "getproviderlist":
                    // 使用ajax取得供应商下拉列表
                    this.getproviderlist(req, resp);
                    break;
                case "add":
                    // 添加订单
                    this.saveBill(req, resp);
                    break;
                case "view":
                    // 查看订单
                    this.getBillById(req, resp, "billview.jsp");
                    break;
                case "modify":
                    // 修改页面初始化
                    this.getBillById(req, resp, "billmodify.jsp");
                    break;
                case "modifysave":
                    // 修改订单信息
                    this.updateBillById(req, resp);
                    break;
                case "delbill":
                    // 根据id删除订单
                    this.deleteBillById(req, resp);
                    break;
            }
        }
    }

    /**
     * 根据id删除订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteBillById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billId = req.getParameter("billid");

        BillService billService = new BillServiceImpl();
        String delResult = billService.deleteBillById(billId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("delResult", delResult);

        //把resultMap转换成json对象输出
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    /**
     * 根据id修改订单信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateBillById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billId = req.getParameter("id");
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setId(Integer.parseInt(billId));
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        BillService billService = new BillServiceImpl();
        billService.updateBillById(bill);

        resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
    }

    /**
     * 根据id查询订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void getBillById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String billId = req.getParameter("billid");

        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(billId);

        req.setAttribute("bill", bill);

        req.getRequestDispatcher(url).forward(req, resp);
    }

    /**
     * 添加订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void saveBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());

        BillService billService = new BillServiceImpl();
        billService.saveBill(bill);

        resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
    }

    /**
     * 使用ajax取得供应商下拉列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void getproviderlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取得供应商下拉列表
        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.listProviderForSelect();

        //把resultMap转换成json对象输出
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    /**
     * 查询订单列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void listBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取得查询条件
        String productName = req.getParameter("queryProductName");
        String providerId = req.getParameter("queryProviderId");
        String isPayment = req.getParameter("queryIsPayment");

        BillService billService = new BillServiceImpl();
        List<Bill> billList = billService.listBill(productName, providerId, isPayment);

        // 取得供应商下拉列表
        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.listProviderForSelect();

        req.setAttribute("billList", billList);
        req.setAttribute("providerList", providerList);
        req.setAttribute("queryProductName", productName);
        req.setAttribute("queryProviderId", providerId);
        req.setAttribute("queryIsPayment", isPayment);

        req.getRequestDispatcher("billlist.jsp").forward(req, resp);
    }
}
