package com.example.libmanagement;

import com.example.libmanagement.user.UserDashboard;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private ComboBox<String> class_v;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private PasswordField password;
    @FXML
    private TextField studentname;
    @FXML
    private TextField username;

    Connection conn = null;
    @FXML
    void close(ActionEvent event) {
        // Đóng cửa sổ hiện tại
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

        // Thoát ứng dụng
        Platform.exit(); // Kết thúc ứng dụng JavaFX một cách gọn gàng
        System.exit(0);  // Kết thúc toàn bộ chương trình
    }
    @FXML
    void minimize(ActionEvent event)
    {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        System.out.println("minimize signal");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        class_v.getItems().addAll("1/1","1/2","1/3","2/1","2/2","2/3",
                "3/1","3/2","3/3","4/1","4/2","4/3","5/1","5/2","5/3");
        System.out.println("set combobox complete");

    }
    public void register(ActionEvent event) throws IOException {
        String class_data = class_v.getValue();
        String password_data = password.getText();
        String studentname_data = studentname.getText();
        String username_data = username.getText();
        String id_data = username_data + class_data;
        System.out.println("class_data: "+ class_data);
        System.out.println("password_data: " + password_data);
        System.out.println("studentname_data: " + studentname_data);
        System.out.println("username_data: " + username_data);
        System.out.println("reenter password: " + confirmpassword.getText());
        if(class_data == "null" || password_data.isEmpty() || studentname_data.isEmpty() || username_data.isEmpty() || id_data.isEmpty())
        {
            System.out.println("tín hiệu 1");
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);

//            Image image = new Image("pokeball.png");
//            ImageView imageView = new ImageView(image);
//            imageView.setFitHeight(48);
//            imageView.setFitWidth(48);
//            warningAlert.setGraphic(imageView);

            warningAlert.setTitle("Cảnh báo");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Không được để trống!");
            warningAlert.showAndWait();
        }
        else if(!(password.getText().equals(confirmpassword.getText())))
        {
            System.out.println("tín hiệu 2");
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);

//            Image image = new Image("pokeball.png");
//            ImageView imageView = new ImageView(image);
//            imageView.setFitHeight(48);
//            imageView.setFitWidth(48);
//            warningAlert.setGraphic(imageView);

            warningAlert.setTitle("Cảnh báo");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Mật khẩu không trùng nhau!");
            warningAlert.showAndWait();
        }
        else {
            try
            {
                conn = mysqlconnect.ConnectDb();
                if (conn == null)
                {
                    System.out.println("Connection is NULL !!!!");
                }
                Statement statement = conn.createStatement();

                statement.executeUpdate("INSERT INTO jdbc.user "
                        + "(iduser, student_name, class, username, password) "
                        + "VALUES ('" + id_data + "', '" + studentname_data + "', '" + class_data + "', '" + username_data + "', '" + password_data + "')");



            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            username.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("User_Dashboard.fxml")));
            Parent root = loader.load();

            UserDashboard userDashboard = loader.getController();
            userDashboard.setUsername(studentname_data);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Library Management");
            stage.setScene(scene);
            stage.setResizable(false);
            Image icon = new Image("book.png");
            stage.getIcons().add(icon);

            stage.show();

        }

    }
}
