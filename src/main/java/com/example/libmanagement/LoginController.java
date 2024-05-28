package com.example.libmanagement;

import com.example.libmanagement.user.UserDashboard;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import java.sql.ResultSet;

public class LoginController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    private Label password_action;
    @FXML
    private Label username_action;


    private Stage stage;
    private Scene scene;
    private Parent root;
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

    public void login(ActionEvent event) throws IOException, SQLException {
        String username_v = username.getText().trim();
        String password_v = password.getText().trim();
        if (username.getText().trim().equals("admin") && password.getText().trim().equals("123456")) {
            System.out.println("Login Successful");

        }
        else if(username_v.isEmpty() && password_v.isEmpty())
        {
            username_action.setText("Empty Username");
            password_action.setText("Empty Password");
        }
        else if (username_v.isEmpty())
        {
            username_action.setText("Empty Username");
        }
        else if (password_v.isEmpty())
        {
            password_action.setText("Empty Password");
        }
        else {
            conn = mysqlconnect.ConnectDb();
            if (conn == null)
            {
                System.out.println("Connection is NULL !!!!");
            }
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT password FROM jdbc.user WHERE username = '"+ username_v +"'");
            String passwordFromDB = "";
            if (rs.next()) {
                passwordFromDB = rs.getString("password");
                System.out.println("Password: " + passwordFromDB);
                if(passwordFromDB.equals(password_v))
                {
                    username.getScene().getWindow().hide();

                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("User_Dashboard.fxml")));
                    Parent root = loader.load();


                    UserDashboard userDashboard = loader.getController();
                    userDashboard.setUsername(username_v);

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Library Management");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    Image icon = new Image("book.png");
                    stage.getIcons().add(icon);

                    stage.show();
                }
            } else {
                passwordFromDB = "";
                System.out.println("User not found.");
                username_action.setText("Incorrect Username");
                password_action.setText("Incorrect Password");
                System.out.println("Login Failed");
            }

        }
    }
    public void register(ActionEvent event) throws IOException
    {
        username.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Library Management");
        stage.setScene(scene);
        stage.setResizable(false);
        Image icon = new Image("book.png");
        stage.getIcons().add(icon);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

}
