package com.example.sistemagerencial;

public class Product {
    private String productId;
    private String product_name;
    private Integer product_price;
    private Integer product_store;
    private Integer product_stock;


    public Product(String productId,String product_name, Integer product_price, Integer product_store, Integer product_stock){
        this.productId = productId;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_store = product_store;
        this.product_stock = product_stock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public Integer getProduct_store() {
        return product_store;
    }

    public void setProduct_store(Integer product_store) {
        this.product_store = product_store;
    }

    public Integer getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(Integer product_stock) {
        this.product_stock = product_stock;
    }
}
