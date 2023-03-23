package com.buba.servlet.bill;

import com.buba.pojo.Bill;
import com.buba.service.bill.BillService;
import com.buba.service.bill.BillServiceImpl;

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
 * @description: TODO
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
        BillService billService = new BillServiceImpl();
        List<Bill> billList = billService.listBIll();

        req.setAttribute("billList", billList);
        req.getRequestDispatcher("billlist.jsp").forward(req, resp);
    }
}
