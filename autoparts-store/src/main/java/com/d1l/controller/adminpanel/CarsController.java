package com.d1l.controller.adminpanel;

import com.d1l.dao.CarDao;
import com.d1l.model.Car;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarsController extends ActionSupport {

    private Car car;
    private List<Car> carsList;
    private int id;

    @Override
    public String execute() throws Exception {
        carsList = CarDao.getCarsList();
        return Action.SUCCESS;
    }

    public String update() {
        if (!validate(getCar())) return Action.SUCCESS;
        CarDao.addOrUpdateCar(getCar());
        return Action.SUCCESS;
    }

    public String delete() {
        CarDao.deleteCar(getId());
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getCar())) return Action.SUCCESS;
        CarDao.addOrUpdateCar(getCar());
        return Action.SUCCESS;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
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

    private boolean validate(Car car)
    {
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,100}$");
        Matcher m = namePattern.matcher(car.getName());
        if (!m.matches())
        {
            errorString = "The name is invalid";
            return false;
        }
        Pattern yearPattern = Pattern.compile("^[0-9]{3,5}$");
        m = yearPattern.matcher(String.valueOf(car.getReleaseYear()));
        if (!m.matches())
        {
            errorString = "The release year is invalid";
            return false;
        }
        return true;
    }

}
