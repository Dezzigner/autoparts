package com.d1l.controller;

import com.d1l.dao.DetailDao;
import com.d1l.model.Detail;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class FindDetailsContoller extends ActionSupport {
    private List<Detail> detailsList;

    public List<Detail> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Detail> detailsList) {
        this.detailsList = detailsList;
    }

    @Override
    public String execute() throws Exception {
        detailsList = DetailDao.getDetailsList();
        return Action.SUCCESS;
    }
}

