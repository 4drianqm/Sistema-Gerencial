module com.example.sistemagerencial {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;


    opens com.example.sistemagerencial to javafx.fxml;
    exports com.example.sistemagerencial;
    exports com.example.sistemagerencial.Controllers;

}