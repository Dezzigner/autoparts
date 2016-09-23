package com.d1l.model;

import javax.persistence.*;

@Entity
@Table(name = "autoparts.detail")
public class Detail {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "count_in_warehouse")
    private int countInWarehouse;
    @ManyToOne(optional = false)
    @JoinColumn(name="car_id")
    private Car car;
    @ManyToOne(optional = false)
    @JoinColumn(name="warehouse_id")
    private Warehouse warehouse;
    @ManyToOne(optional = false)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getCountInWarehouse() {
        return countInWarehouse;
    }

    public void setCountInWarehouse(int countInWarehouse) {
        this.countInWarehouse = countInWarehouse;
    }
}
