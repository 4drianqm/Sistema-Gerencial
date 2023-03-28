package com.example.sistemagerencial.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField user_txt;
    public TextField password_txt;
    public Button login_txt;
    public AnchorPane main_from;

    public void loginAdmin() throws IOException {
        login_txt.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}


