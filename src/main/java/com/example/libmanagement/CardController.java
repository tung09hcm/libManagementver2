package com.example.libmanagement;

import com.example.libmanagement.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

    public void setData (Book book)
    {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        bookimage.setImage(image);

        bookname.setText(book.getName());
        author.setText(book.getAuthor());
        returndate.setText(book.getReturndate());

    }
    public void getInfromationofCard()
    {
        System.out.println("bookname: " + bookname.getText());
        System.out.println("author: " + author.getText());
        System.out.println("returndate: " + returndate.getText());
        System.out.println("===============================");
    }

}