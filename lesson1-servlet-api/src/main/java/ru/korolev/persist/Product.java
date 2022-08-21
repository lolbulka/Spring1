package ru.korolev.persist;

public class Product {
    private long id;
    private String productName;
    private long cost;

    public void setId(long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public long getCost() {
        return cost;
    }

    public Product(String productName, long cost) {
        this.productName = productName;
        this.cost = cost;
    }
}
