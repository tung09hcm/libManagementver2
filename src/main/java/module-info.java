module com.example.libmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.libmanagement to javafx.fxml;
    exports com.example.libmanagement;
}