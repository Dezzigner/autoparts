package com.d1l.controller.adminpanel;

import com.d1l.dao.WarehouseDao;
import com.d1l.model.Warehouse;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WarehousesController extends ActionSupport {

    private Warehouse warehouse;
    private List<Warehouse> warehousesList;
    private int id;

    @Override
    public String execute() throws Exception {
        warehousesList = WarehouseDao.getWarehousesList();
        return Action.SUCCESS;
    }

    public String update() {
        if (!validate(getWarehouse())) return Action.SUCCESS;
        WarehouseDao.addOrUpdateWarehouse(getWarehouse());
        return Action.SUCCESS;
    }

    public String delete() {
        WarehouseDao.deleteWarehouse(getId());
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getWarehouse())) return Action.SUCCESS;
        WarehouseDao.addOrUpdateWarehouse(getWarehouse());
        return Action.SUCCESS;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<Warehouse> getWarehousesList() {
        return warehousesList;
    }

    public void setWarehousesList(List<Warehouse> warehousesList) {
        this.warehousesList = warehousesList;
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

    private boolean validate(Warehouse warehouse)
    {
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,100}$");
        Matcher m = namePattern.matcher(warehouse.getName());
        if (!m.matches())
        {
            errorString = "The name is invalid";
            return false;
        }
        Pattern addressPattern = Pattern.compile("^[A-Za-z0-9,.\\s]{1,250}$");
        m = addressPattern.matcher(warehouse.getAddress());
        if (!m.matches())
        {
            errorString = "The address is invalid";
            return false;
        }
        return true;
    }
}
