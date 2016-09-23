package com.d1l.controller.customer;

import com.d1l.dao.*;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MakeOrderController extends ActionSupport {

    private List<DetailReport> detailsList;

    public List<DetailReport> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<DetailReport> detailsList) {
        this.detailsList = detailsList;
    }

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();

        List<DetailInfoForOrder> details;

        if (session.containsKey("detailsForOrder")) {
            details = (List<DetailInfoForOrder>)session.get("detailsForOrder");
        } else {
            return Action.SUCCESS;
        }

        detailsList = new ArrayList<DetailReport>();
        for (DetailInfoForOrder detailInfo : details) {
            DetailReport detailReport = new DetailReport();
            detailReport.setDetail(DetailDao.getDetailById(detailInfo.getDetailId()));
            detailReport.setCount(detailInfo.getCount());
            detailsList.add(detailReport);
        }

        return Action.SUCCESS;
    }

    public String makeOrder() throws Exception {
        Map session = ActionContext.getContext().getSession();

        List<DetailInfoForOrder> details;

        if (session.containsKey("detailsForOrder")) {
            details = (List<DetailInfoForOrder>)session.get("detailsForOrder");
        } else {
            return Action.SUCCESS;
        }

        Order order = new Order();
        Customer customer = CustomerDao.getCustomerByUser(
                UserDao.getUserById(Integer.parseInt(session.get("id").toString())));
        order.setCustomer(customer);
        order.setDate(new Date());
        OrderDao.addOrUpdateOrder(order);
        order = OrderDao.getOrderByCustomerAndLastDate(customer);

        for (DetailInfoForOrder detailInfo : details) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setCount(detailInfo.getCount());
            orderDetail.setDetailId(detailInfo.getDetailId());
            Detail d = DetailDao.getDetailById(detailInfo.getDetailId());
            int c = d.getCountInWarehouse();
            d.setCountInWarehouse(c - detailInfo.getCount());
            DetailDao.addOrUpdateDetail(d);
            OrderDetailDao.addOrUpdateOrderDetail(orderDetail);
        }

        session.remove("detailsForOrder");

        return Action.SUCCESS;
    }

}
