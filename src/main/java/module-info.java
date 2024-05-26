module com.example.libmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.libmanagement.admin to javafx.fxml;

    opens com.example.libmanagement to javafx.fxml;
    opens com.example.libmanagement.admin to javafx.fxml;
    exports com.example.libmanagement;
}