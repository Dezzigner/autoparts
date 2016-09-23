package com.d1l.controller.customer;

import com.d1l.dao.*;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersController extends ActionSupport {

    private List<Order> ordersList;
    private List<OrderDetail> orderDetails;
    private List<OrderReport> orderReports;

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        if(session.containsKey("id")) {
            ordersList = OrderDao.getOrdersListByCustomer(
                    CustomerDao.getCustomerByUser(
                            UserDao.getUserById(Integer.parseInt(session.get("id").toString()))));

            orderReports = new ArrayList<OrderReport>();
            for (Order order : ordersList) {
                orderDetails = OrderDetailDao.getOrderDetailsByOrderId(order.getId());

                int amount = 0;
                List<DetailReport> detailReports = new ArrayList<DetailReport>();
                for (OrderDetail orderDetail : orderDetails) {
                    Detail detail = DetailDao.getDetailById(orderDetail.getDetailId());

                    DetailReport detailReport = new DetailReport();
                    detailReport.setDetail(detail);
                    detailReport.setCount(orderDetail.getCount());
                    detailReports.add(detailReport);

                    amount += detail.getPrice();
                }

                OrderReport orderReport = new OrderReport();
                orderReport.setCustomer(CustomerDao.getCustomerById(order.getCustomer().getId()));
                orderReport.setDetailsList(detailReports);
                orderReport.setAmount(amount);
                orderReport.setId(order.getId());
                orderReports.add(orderReport);
            }
        }
        return Action.SUCCESS;
    }

    public List<OrderReport> getOrderReports() {
        return orderReports;
    }

    public void setOrderReports(List<OrderReport> orderReports) {
        this.orderReports = orderReports;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
