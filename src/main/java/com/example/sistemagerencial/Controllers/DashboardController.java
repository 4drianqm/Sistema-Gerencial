package com.example.sistemagerencial.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    public Button inventory_btn;
    public Button clients_btn;
    public Button providers_btn;
    public AnchorPane Inventory_form;
    public AnchorPane clients_form;
    public TableView Inventory_tbl;
    public TableColumn Inventory_name;
    public TableColumn Inventory_price;
    public TableColumn Inventory_cost;
    public TableColumn Inventory_store;
    public TableColumn Inventory_exist;
    public TableColumn Inventory_insert;
    public TableColumn Inventory_delete;

    public void switchForm(ActionEvent event){
        if(event.getSource() == inventory_btn){
            Inventory_form.setVisible(true);
            clients_form.setVisible(false);
        }else if(event.getSource() == clients_btn){
            Inventory_form.setVisible(false);
            clients_form.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
