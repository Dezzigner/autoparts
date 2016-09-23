package com.d1l.controller.customer;

import com.d1l.dao.DetailDao;
import com.d1l.dao.WarehouseDao;
import com.d1l.model.Detail;
import com.d1l.model.DetailInfoForOrder;
import com.d1l.model.Warehouse;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailsController extends ActionSupport {

    private List<Detail> detailsList;
    private int id;

    private int detailId;
    private int count;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String execute() throws Exception {
        detailsList = DetailDao.getDetailsList();
        return Action.SUCCESS;
    }

    public String addDetailForOrder() throws Exception {
        Map session = ActionContext.getContext().getSession();

        List<DetailInfoForOrder> details;

        if (session.containsKey("detailsForOrder")) {
            details = (List<DetailInfoForOrder>)session.get("detailsForOrder");
        } else {
            details = new ArrayList<DetailInfoForOrder>();
        }

        if (DetailDao.getDetailById(detailId).getCountInWarehouse() < count) {
            message = "Not in warehouse as much detail";
            return Action.SUCCESS;
        }

        DetailInfoForOrder detailInfo = new DetailInfoForOrder();
        detailInfo.setDetailId(detailId);
        detailInfo.setCount(count);
        details.add(detailInfo);
        session.put("detailsForOrder", details);

        return Action.SUCCESS;
    }

    public List<Detail> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Detail> detailsList) {
        this.detailsList = detailsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
