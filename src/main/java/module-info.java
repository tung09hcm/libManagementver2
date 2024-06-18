module com.example.libmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens com.example.libmanagement to javafx.fxml;
    opens com.example.libmanagement.user to javafx.fxml;
    exports com.example.libmanagement;
    exports com.example.libmanagement.user;

}