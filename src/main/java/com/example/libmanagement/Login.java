package com.example.libmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("User_Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Library Management");
        stage.setResizable(false);
        stage.setScene(scene);

        Image icon = new Image("book.png");
        stage.getIcons().add(icon);

        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}