package com.example.sistemagerencial;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Product {
    private String productId;
    private String product_name;
    private Integer product_price;
    private Integer product_store;
    private Integer product_stock;

    private Button edit;
    private Button delete;


    public Product(String productId,String product_name, Integer product_price, Integer product_store, Integer product_stock,Button delete,Button edit){
        this.productId = productId;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_store = product_store;
        this.product_stock = product_stock;
        this.edit = edit;
        this.delete = delete;


        edit.setTextAlignment(TextAlignment.CENTER);
        edit.setTextFill(Color.web("ffff"));
        edit.setPadding(new Insets(5));
        edit.setStyle("-fx-background-color: #407072;");

        delete.setTextAlignment(TextAlignment.CENTER);
        delete.setTextFill(Color.web("ffff"));
        delete.setStyle("-fx-background-color: #C24240;");
        delete.setPadding(new Insets(5));
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
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
