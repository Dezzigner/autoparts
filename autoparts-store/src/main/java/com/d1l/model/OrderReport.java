package com.d1l.model;

import java.util.List;

public class OrderReport {

    private Customer customer;
    private List<DetailReport> detailsList;
    private int amount;
    private int id;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<DetailReport> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<DetailReport> detailsList) {
        this.detailsList = detailsList;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
