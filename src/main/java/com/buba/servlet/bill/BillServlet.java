package com.buba.servlet.bill;

import com.buba.pojo.Bill;
import com.buba.pojo.Provider;
import com.buba.service.bill.BillService;
import com.buba.service.bill.BillServiceImpl;
import com.buba.service.provider.ProviderService;
import com.buba.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        if (method != null && "query".equals(method)) {
            // 查询订单列表
            this.listBill(req, resp);
        } else {
            // TODO 添加订单
        }
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
