package com.servlet;

public class Product {
    private int id;
    private String name;
    private int price;

    public Product() {
        this.id = 0;
        this.name = "";
        this.price = 0;
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return "id: " + this.id + ", name: " + this.name
                + ", price: " + this.price + ".";
    }
}
