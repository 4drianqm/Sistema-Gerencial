package com.example.sistemagerencial.Controllers;

import com.example.sistemagerencial.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class updateClientController implements Initializable {
    public TextField txt_updateName;
    public TextField txt_updateAddress;
    public TextField txt_updateNit;
    public Button btn_updateUpdate;
    public TextField txt_updateId;
    public TextField txt_updatePhone;

    private DashboardController dashboardController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void update_Client(){

        Connection connection = new Connection();

        String id   = txt_updateId.getText();
        String name = txt_updateName.getText();
        String phone = txt_updatePhone.getText();
        String address = txt_updateAddress.getText();
        String nit = txt_updateNit.getText();


        connection.updateClientDB(id,name,phone,address,nit);
        Stage stage = (Stage) btn_updateUpdate.getScene().getWindow();
        stage.close();
        dashboardController.None();
        dashboardController.reloadClients();
    }

    public void setDashboardController(DashboardController dashboardController){
        this.dashboardController = dashboardController;
    }
}
