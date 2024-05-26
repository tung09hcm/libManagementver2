package com.example.libmanagement.user;

import com.example.libmanagement.Book;
import com.example.libmanagement.CardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDashboard implements Initializable {
    @FXML
    private HBox cardlayout;
    @FXML
    private Label username;
    private List<Book> recentlyAdded;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdd());
        try {
            for (Book book : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookSample.fxml"));
                System.out.println("pass thourgh setlocation");

                HBox cardBox = fxmlLoader.load();
                System.out.println("pass thourgh load");

                CardController cardController = fxmlLoader.getController();
                cardController.setData(book);
                cardlayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void setUsername(String username_v)
    {
        username.setText(username_v);
    }

    private List<Book> recentlyAdd()
    {
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setName("A Brief History of \n" +
                "Humankind");
        book.setAuthor("Yuval Noah Harari\n");
        book.setImageSrc("/books/history_of_human.png");
        ls.add(book);
        return ls;
    }
}
