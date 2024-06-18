package com.example.libmanagement;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class CardDisplayController {
    @FXML
    private Label authorname;
    @FXML
    private JFXButton more_button;
    @FXML
    private Label bookname;
    @FXML
    private ImageView image;
    public String username_v;
    //public Image image_data_v;
    public void setData(Book book)
    {
        Image image_data = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        //image_data_v = image_data;
        image.setImage(image_data);

        bookname.setText(book.getName());
        authorname.setText(book.getAuthor());
    }
    public void SwitchBorrowSceneAction(ActionEvent event) throws IOException, SQLException {
        System.out.println(bookname.getText());
        System.out.println(authorname.getText());
        System.out.println(username_v);
    }
    public void setUsername(String username)
    {
        username_v = username;
    }
}
