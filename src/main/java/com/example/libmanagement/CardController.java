package com.example.libmanagement;

import com.example.libmanagement.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Objects;

public class CardController {
    @FXML
    private Label author;
    @FXML
    private Label bookname;
    @FXML
    private Label returndate;
    @FXML
    private HBox box;
    @FXML
    private ImageView bookimage;

    public String username_v;
    private String [] colors = {"7b9cb0","8275d1", "cf848f","469e8a"};
    public void setData (Book book)
    {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        bookimage.setImage(image);

        bookname.setText(book.getName());
        author.setText(book.getAuthor());
        returndate.setText(book.getReturndate());

        box.setStyle("-fx-background-color: #" + (colors[(int)(Math.random()*colors.length)]));

    }
    public void getInfromationofCard()
    {
        System.out.println("bookname: " + bookname.getText());
        System.out.println("author: " + author.getText());
        System.out.println("returndate: " + returndate.getText());
        System.out.println("===============================");
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

        // Thiết lập tiêu đề cho thông báo
        infoAlert.setTitle("Thông báo");

        // Thiết lập tiêu đề đầu không có nội dung (null)
        infoAlert.setHeaderText(null);

        // Thiết lập nội dung chính của thông báo
        infoAlert.setContentText("Trả thành công!");

        // Hiển thị thông báo và chờ người dùng đóng nó
        infoAlert.showAndWait();

    }

    public void setUsername(String username)
    {
        username_v = username;
    }

}
