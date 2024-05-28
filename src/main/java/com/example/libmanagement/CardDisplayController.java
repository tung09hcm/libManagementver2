package com.example.libmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CardDisplayController {
    @FXML
    private Label authorname;
    @FXML
    private Label bookname;
    @FXML
    private ImageView image;

    public void setData(Book book)
    {
        Image image_data = new Image(Objects.requireNonNull(getClass().getResourceAsStream(book.getImageSrc())));
        image.setImage(image_data);

        bookname.setText(book.getName());
        authorname.setText(book.getAuthor());
    }
}
