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
import java.util.List;

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
            }
        }
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
        List<Bill> billList = billService.listBIll(productName, providerId, isPayment);

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
