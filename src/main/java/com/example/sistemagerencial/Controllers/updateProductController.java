package com.example.sistemagerencial.Controllers;

import com.example.sistemagerencial.Connection;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;

public class updateProductController implements Initializable {
    public TextField txt_updateProduct_name;
    public TextField txt_updateProduct_price;
    public TextField txt_updateProduct_store;
    public TextField txt_updateProduct_exis;
    public TextField txt_updateProducto_id;
    public Button btn_updateProduct_update;

    private DashboardController dashboardController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void update_product(){
        Connection connection =  new Connection();
        String id = txt_updateProducto_id.getText();
        String name = txt_updateProduct_name.getText();
        Integer price = Integer.valueOf(txt_updateProduct_price.getText());
        Integer store = Integer.valueOf(txt_updateProduct_store.getText());
        Integer stock = Integer.valueOf(txt_updateProduct_exis.getText());

        connection.updateProduct(id,name,price,store,stock);
        Stage stage = (Stage) btn_updateProduct_update.getScene().getWindow();
        stage.close();
        dashboardController.clean_load_Inventory();
        dashboardController.None();
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }
}
