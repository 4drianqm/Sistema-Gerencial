package com.example.sistemagerencial.Controllers;

import com.example.sistemagerencial.Connection;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;

public class insertProductController implements Initializable {
    public TextField txt_insertProduct_name;
    public TextField txt_insertProduct_price;
    public TextField txt_insertProduct_store;
    public TextField txt_insertProduct_stoke;
    public Button btn_insertarProducto_add;

    private DashboardController dashboardController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void insert_new_Product(){
        Connection connection = new Connection();
        String name = txt_insertProduct_name.getText();
        Integer price = Integer.valueOf(txt_insertProduct_price.getText());
        Integer store =  Integer.valueOf(txt_insertProduct_store.getText());
        Integer stock = Integer.valueOf(txt_insertProduct_stoke.getText());

        connection.insertNewProduct(name,price,store,stock);
        Stage stage = (Stage) btn_insertarProducto_add.getScene().getWindow();
        stage.close();

        dashboardController.clean_load_Inventory();
        dashboardController.None();

    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }
}
