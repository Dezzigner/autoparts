package com.d1l.controller.supplier;

import com.d1l.dao.*;
import com.d1l.model.Car;
import com.d1l.model.Detail;
import com.d1l.model.Supplier;
import com.d1l.model.Warehouse;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailsController extends ActionSupport {
    
    private Detail detail;
    private List<Detail> detailsList;
    private int id;
    private List<Car> carsList;
    private List<Warehouse> warehousesList;

    public List<Car> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }

    public List<Warehouse> getWarehousesList() {
        return warehousesList;
    }

    public void setWarehousesList(List<Warehouse> warehousesList) {
        this.warehousesList = warehousesList;
    }

    @Override
    public String execute() throws Exception {
        detailsList = DetailDao.getDetailsList();
        carsList = CarDao.getCarsList();
        warehousesList = WarehouseDao.getWarehousesList();
        return Action.SUCCESS;
    }

    public String update() {
        if (!validate(getDetail())) return Action.SUCCESS;
        DetailDao.addOrUpdateDetail(getDetail());
        return Action.SUCCESS;
    }

    public String delete() {
        DetailDao.deleteDetail(getId());
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getDetail())) return Action.SUCCESS;
        getDetail().setCar(CarDao.getCarById(getDetail().getCar().getId()));
        getDetail().setWarehouse(WarehouseDao.getWarehouseById(getDetail().getWarehouse().getId()));
        Map session = ActionContext.getContext().getSession();
        if (session.containsKey("id")) {
            Supplier supplier = SupplierDao.getSupplierByUser(
                    UserDao.getUserById(Integer.parseInt(session.get("id").toString())));
            getDetail().setSupplier(supplier);
        }
        DetailDao.addOrUpdateDetail(getDetail());
        return Action.SUCCESS;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
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

    private String errorString;

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    private boolean validate(Detail detail)
    {
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,100}$");
        Matcher m = namePattern.matcher(detail.getName());
        if (!m.matches())
        {
            errorString = "The name is invalid";
            return false;
        }
        return true;
    }


}
