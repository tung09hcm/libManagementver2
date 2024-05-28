package com.example.libmanagement.user;

import com.example.libmanagement.Book;
import com.example.libmanagement.CardController;
import com.example.libmanagement.CardDisplayController;
import com.example.libmanagement.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
public class UserDashboard implements Initializable {
    @FXML
    private ScrollPane scrollpane0;
    @FXML
    private HBox cardlayout;
    @FXML
    private GridPane bookcontainer;
    @FXML
    private Label username;

    @FXML
    private AnchorPane HomePane;
    @FXML
    private AnchorPane CatagoryPanel;

    @FXML
    private JFXButton AuthorButton;
    @FXML
    private JFXButton AvailableButton;
    @FXML
    private JFXButton BorrowedButton;
    @FXML
    private JFXButton CatagoryButton;
    @FXML
    private JFXButton HomeButton;
    private List<Book> recentlyAdded;
    private List<Book> recommendBook;
    private Stage stage;
    private Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HomePane.setVisible(true);
        CatagoryPanel.setVisible(false);
        recentlyAdded = new ArrayList<>(recentlyAdd());
        recommendBook = new ArrayList<>(recommendAdd());
        scrollpane0.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane0.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                event.consume();
            }
        });
        int column = 0;
        int row = 1;
        try {
            for (Book book : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookSample.fxml"));
                // System.out.println("pass thourgh setlocation");

                HBox cardBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardController cardController = fxmlLoader.getController();
                cardController.setData(book);

                cardlayout.getChildren().add(cardBox);

            }
            for (Book book : recommendBook) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookDisplay.fxml"));
                // System.out.println("pass thourgh setlocation");

                VBox bookBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardDisplayController cardDisplayController = fxmlLoader.getController();
                cardDisplayController.setData(book);

                if(column == 5)
                {
                    column = 0;
                    ++row;
                }

                bookcontainer.add(bookBox,column++,row);
                GridPane.setMargin(bookBox, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void setUsername(String username_v)
    {
        username.setText(username_v);
    }
    public void switchForm(ActionEvent e)
    {
        System.out.println("==================================");
        System.out.println("switchform signa");
        System.out.println("Source: " + e.getSource().toString());
        System.out.println("==================================");
        if(e.getSource() == CatagoryButton)
        {
            System.out.println("CatagoryButton signal");
            HomePane.setVisible(false);
            CatagoryPanel.setVisible(true);
        }
        else if (e.getSource() == AuthorButton)
        {
            System.out.println("AuthorButton signal");

        }
        else if (e.getSource() == AvailableButton)
        {
            System.out.println("AvailableButton signal");
        }
        else if (e.getSource() == BorrowedButton)
        {
            System.out.println("BorrowedButton signal");
        }
        else if (e.getSource() == HomeButton)
        {
            System.out.println("HomeButton signal");
            HomePane.setVisible(true);
            CatagoryPanel.setVisible(false);
        }
        else {
            System.out.println("something went wrong");
        }
    }
    private List<Book> recentlyAdd()
    {
        List<Book> ls = new ArrayList<>();

        Book book = new Book();
        book.setName("A Brief History of \n" +
                "Humankind");
        book.setAuthor("Yuval Noah Harari\n");
        book.setImageSrc("/books/history_of_human.png");
        book.setReturndate("20/6/2024");
        ls.add(book);

        Book book1 = new Book();
        book1.setName("No game \n\t No life 4");
        book1.setAuthor("Yuu Kamiya\n");
        book1.setImageSrc("/books/ngnl4.png");
        ls.add(book1);

        Book book2 = new Book();
        book2.setName("No game \n\t No life 5");
        book2.setAuthor("Yuu Kamiya\n");
        book2.setImageSrc("/books/ngnl5.png");
        ls.add(book2);

        Book book3 = new Book();
        book3.setName("Angel Next Door 3");
        book3.setAuthor("Saekisan\n");
        book3.setImageSrc("/books/angel3.png");
        ls.add(book3);

        Book book4 = new Book();
        book4.setName("No game \n\t No life 6");
        book4.setAuthor("Yuu Kamiya\n");
        book4.setImageSrc("/books/ngnl6.png");
        ls.add(book4);
        return ls;
    }
    private List<Book> recommendAdd()
    {
        List<Book> ls = new ArrayList<>();

        Book book = new Book();
        book.setName("A Brief History of \n" +
                "Humankind");
        book.setAuthor("Yuval Noah Harari\n");
        book.setImageSrc("/books/history_of_human.png");
        book.setReturndate("20/6/2024");
        ls.add(book);

        Book book1 = new Book();
        book1.setName("No game \n\t No life 4");
        book1.setAuthor("Yuu Kamiya\n");
        book1.setImageSrc("/books/ngnl4.png");
        ls.add(book1);

        Book book2 = new Book();
        book2.setName("No game \n\t No life 5");
        book2.setAuthor("Yuu Kamiya\n");
        book2.setImageSrc("/books/ngnl5.png");
        ls.add(book2);

        Book book3 = new Book();
        book3.setName("Angel Next Door 3");
        book3.setAuthor("Saekisan\n");
        book3.setImageSrc("/books/angel3.png");
        ls.add(book3);

        Book book4 = new Book();
        book4.setName("No game \n\t No life 6");
        book4.setAuthor("Yuu Kamiya\n");
        book4.setImageSrc("/books/ngnl6.png");
        ls.add(book4);

        Book book5 = new Book();
        book5.setName("Angel Next Door 5");
        book5.setAuthor("Saekisan\n");
        book5.setImageSrc("/books/angel5.png");
        ls.add(book5);

        return ls;
    }

}
