package com.phm.bean;

public class LoginBean {

    private int id;
    private String Name;
    private String Brand;
    private double Price;
    private int Stock;

    // Constructors
    public LoginBean() {
    }

    public LoginBean(int id, String Name, String Brand, double Price, int Stock) {
        this.id = id;
        this.Name = Name;
        this.Brand = Brand;
        this.Price = Price;
        this.Stock = Stock;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
}
