package com.example.libmanagement;

import com.example.libmanagement.user.UserDashboard;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ComboBox<String> class_v;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private AnchorPane login_panel;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private Label password_action;

    @FXML
    private JFXButton register;

    @FXML
    private AnchorPane register_panel;

    @FXML
    private TextField studentname;

    @FXML
    private TextField username;

    @FXML
    private TextField username1;

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
    public static String username_vv = "";
    @FXML
    void minimize(ActionEvent event)
    {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        System.out.println("minimize signal");
    }
    public void back_to_login()
    {
        login_panel.setVisible(true);
        register_panel.setVisible(false);
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
                    username_vv = username_v;
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
            }
            else {
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
//        username.getScene().getWindow().hide();
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
//
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setTitle("Library Management");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        Image icon = new Image("book.png");
//        stage.getIcons().add(icon);
//
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
        login_panel.setVisible(false);
        register_panel.setVisible(true);

    }
    public void register1(ActionEvent event) throws IOException {
        String class_data = class_v.getValue();
        String password_data = password1.getText();
        String studentname_data = studentname.getText();
        String username_data = username1.getText();
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
        else if(!(password1.getText().equals(confirmpassword.getText())))
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
                username_vv = username_data;
                statement.executeUpdate("INSERT INTO jdbc.user "
                        + "(iduser, student_name, class, username, password) "
                        + "VALUES ('" + id_data + "', '" + studentname_data + "', '" + class_data + "', '" + username_data + "', '" + password_data + "')");



            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            username.getScene().getWindow().hide();
            username_vv = username_data;
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("User_Dashboard.fxml")));
            Parent root = loader.load();

            UserDashboard userDashboard = loader.getController();
            userDashboard.setUsername(username_data);

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_panel.setVisible(true);
        register_panel.setVisible(false);
        class_v.getItems().addAll("1/1","1/2","1/3","2/1","2/2","2/3",
                "3/1","3/2","3/3","4/1","4/2","4/3","5/1","5/2","5/3");
        System.out.println("set combobox complete");
    }
}
