package com.example.sistemagerencial.Controllers;

import com.example.sistemagerencial.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class insertClientController implements Initializable {

    public TextField txt_nameInsert;
    public TextField txt_addressInsert;
    public TextField txt_nitInsert;
    public TextField txt_idInsert;
    public Button btn_newClient;

    private DashboardController dashboardController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void inert_new_client(){

        Connection connection = new Connection();
        String name  = txt_nameInsert.getText();
        String address = txt_addressInsert.getText();
        String nit = txt_nitInsert.getText();

        connection.insertNewClient(name,address,nit);
        dashboardController.reloadClients();
        Stage stage = (Stage) btn_newClient.getScene().getWindow();
        stage.close();
        dashboardController.None();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }



}
