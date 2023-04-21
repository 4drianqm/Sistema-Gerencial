package com.example.sistemagerencial;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Clients {
    private String clientId;
    private String client_name;
    private String client_address;
    private String client_nit;
    private String cellphone;
    private Button add;
    private Button delete;


    public Clients(String clientId,String client_name,String cellphone, String client_address,String client_nit,Button add,Button delete){
        this.clientId = clientId;
        this.client_name = client_name;
        this.cellphone = cellphone;
        this.client_address = client_address;
        this.client_nit = client_nit;
        this.add = add;
        this.delete = delete;


        add.setTextAlignment(TextAlignment.CENTER);
        add.setTextFill(Color.web("ffff"));
        add.setPadding(new Insets(5));
        add.setStyle("-fx-background-color: #407072;");

        delete.setTextAlignment(TextAlignment.CENTER);
        delete.setTextFill(Color.web("ffff"));
        delete.setStyle("-fx-background-color: #C24240;");
        delete.setPadding(new Insets(5));


    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_adrees) {
        this.client_address = client_adrees;
    }

    public String getClient_nit() {
        return client_nit;
    }

    public void setClient_nit(String client_nit) {
        this.client_nit = client_nit;
    }

    public Button getAdd() {
        return add;
    }

    public void setAdd(Button add) {
        this.add = add;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
