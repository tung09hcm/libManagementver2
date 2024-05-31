package com.example.libmanagement.user;

import com.example.libmanagement.*;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
public class UserDashboard implements Initializable {
    @FXML
    private JFXButton BorrowedButton;

    @FXML
    private AnchorPane Borrowed_Pane;

    @FXML
    private JFXButton CatagoryButton;

    @FXML
    private AnchorPane CatagoryPanel_Reference;

    @FXML
    private ScrollPane CatagoryReference;

    @FXML
    private JFXButton HomeButton;

    @FXML
    private AnchorPane HomePane;

    @FXML
    private ScrollPane NovelReference;

    @FXML
    private ScrollPane TextBookReference;

    @FXML
    private ScrollPane WorkbookReference;

    @FXML
    private GridPane bookcontainer;

    @FXML
    private HBox borrowing;

    @FXML
    private HBox cardlayout;

    @FXML
    private JFXButton novel_button;

    @FXML
    private GridPane novelcontainer;

    @FXML
    private JFXButton reference_book_button;

    @FXML
    private GridPane referencecontainer;

    @FXML
    private HBox request;

    @FXML
    private ScrollPane scrollpane0;

    @FXML
    private ScrollPane scrollpane_borrow;

    @FXML
    private ScrollPane scrollpane_borrow1;

    @FXML
    private JFXButton textbook_button;

    @FXML
    private GridPane textcontainer;

    @FXML
    private Label username;

    @FXML
    private JFXButton workbook_button;

    @FXML
    private GridPane workbookcontainer;


    private List<Book> recentlyAdded;
    private List<Book> recommendBook;
    private List<Book> catagory_reference;
    private List<Book> catagory_workbook;
    private List<Book> catagory_textbook;
    private List<Book> catagory_novel;
    public String username_t;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        HomePane.setVisible(true);
        Borrowed_Pane.setVisible(false);
        CatagoryPanel_Reference.setVisible(false);
        CatagoryReference.setVisible(false);

        System.out.println("==================================");
        System.out.println("USER NAME 1: " + LoginController.username_vv);
        System.out.println("==================================");

        initializeData();

    }

    public void initializeData()
    {
        System.out.println("==================================");
        System.out.println("USER NAME 2: " + username_t);
        System.out.println("==================================");
        recentlyAdded = new ArrayList<>(recentlyAdd());
        try {
            catagory_textbook = new ArrayList<>(catagory_textbook_Add());
            catagory_workbook = new ArrayList<>(catagory_Add("workbook"));
            catagory_reference = new ArrayList<>(catagory_Add("reference_book"));
            catagory_novel = new ArrayList<>(catagory_Add("novel"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        scrollpane0.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane0.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() != 0) {
                event.consume();
            }
        });
        int column = 0;
        int row = 1;
        int column_catagory = 0;
        int row_catagory = 1;
        try {
            for (Book book : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookSample.fxml"));
                // System.out.println("pass thourgh setlocation");

                HBox cardBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardController cardController = fxmlLoader.getController();
                cardController.setUsername(LoginController.username_vv);
                cardController.setData(book);

                cardlayout.getChildren().add(cardBox);

            }
            for (Book book : catagory_textbook) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookDisplay.fxml"));
                // System.out.println("pass thourgh setlocation");

                VBox bookBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardDisplayController cardDisplayController = fxmlLoader.getController();
                cardDisplayController.setData(book);
                cardDisplayController.setUsername(LoginController.username_vv);

                if(column == 5)
                {
                    column = 0;
                    ++row;
                }

                bookcontainer.add(bookBox,column++,row);
                GridPane.setMargin(bookBox, new Insets(10));
            }

            for (Book book : catagory_reference) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookDisplay.fxml"));
                // System.out.println("pass thourgh setlocation");

                VBox bookBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardDisplayController cardDisplayController = fxmlLoader.getController();
                cardDisplayController.setData(book);
                cardDisplayController.setUsername(LoginController.username_vv);

                if(column_catagory == 5)
                {
                    column_catagory = 0;
                    ++row_catagory;
                }
                referencecontainer.add(bookBox,column_catagory++,row_catagory);
//                textcontainer.add(bookBox,column_catagory++,row_catagory);
//                workbookcontainer.add(bookBox,column_catagory++,row_catagory);
//                novelcontainer.add(bookBox,column_catagory++,row_catagory);
                GridPane.setMargin(bookBox, new Insets(10));
            }
            column_catagory = 0; row_catagory = 1;
            for (Book book : catagory_workbook) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookDisplay.fxml"));
                // System.out.println("pass thourgh setlocation");

                VBox bookBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardDisplayController cardDisplayController = fxmlLoader.getController();
                cardDisplayController.setData(book);
                cardDisplayController.setUsername(LoginController.username_vv);

                if(column_catagory == 5)
                {
                    column_catagory = 0;
                    ++row_catagory;
                }
                workbookcontainer.add(bookBox,column_catagory++,row_catagory);
                GridPane.setMargin(bookBox, new Insets(10));
            }
            column_catagory = 0; row_catagory = 1;
            for (Book book : catagory_textbook) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookDisplay.fxml"));
                // System.out.println("pass thourgh setlocation");

                VBox bookBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardDisplayController cardDisplayController = fxmlLoader.getController();
                cardDisplayController.setData(book);
                cardDisplayController.setUsername(LoginController.username_vv);

                if(column_catagory == 5)
                {
                    column_catagory = 0;
                    ++row_catagory;
                }
                textcontainer.add(bookBox,column_catagory++,row_catagory);
                GridPane.setMargin(bookBox, new Insets(10));
            }
            column_catagory = 0; row_catagory = 1;
            for (Book book : catagory_novel) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/libmanagement/BookDisplay.fxml"));
                // System.out.println("pass thourgh setlocation");

                VBox bookBox = fxmlLoader.load();
                // System.out.println("pass thourgh load");

                CardDisplayController cardDisplayController = fxmlLoader.getController();
                cardDisplayController.setData(book);
                cardDisplayController.setUsername(LoginController.username_vv);
                if(column_catagory == 5)
                {
                    column_catagory = 0;
                    ++row_catagory;
                }
                novelcontainer.add(bookBox,column_catagory++,row_catagory);
                GridPane.setMargin(bookBox, new Insets(10));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUsername(String username_v) throws IOException {
        username.setText(username_v);
        username_t = username_v;

    }
    public void switchForm(ActionEvent e) throws IOException {

        System.out.println("==================================");
        System.out.println("switchform signa");
        System.out.println("Source: " + e.getSource().toString());
        System.out.println("==================================");
        if(e.getSource() == CatagoryButton)
        {
            System.out.println("CatagoryButton signal");
            HomePane.setVisible(false);
            reference_book_button.setStyle("-fx-background-color: #727475;");
            CatagoryPanel_Reference.setVisible(true);
            CatagoryReference.setVisible(true);
            Borrowed_Pane.setVisible(false);
            System.out.println("==================================");
            System.out.println("USER NAME: " + username_t);
            System.out.println("==================================");

        }
        else if (e.getSource() == BorrowedButton)
        {
            System.out.println("BorrowedButton signal");
            HomePane.setVisible(false);
            CatagoryPanel_Reference.setVisible(false);
            Borrowed_Pane.setVisible(true);
        }
        else if (e.getSource() == HomeButton)
        {
            System.out.println("HomeButton signal");
            HomePane.setVisible(true);
            CatagoryPanel_Reference.setVisible(false);
            Borrowed_Pane.setVisible(false);
        }
        else {
            System.out.println("something went wrong");
        }
    }
    public void switchFormCatagory(ActionEvent e) throws IOException
    {
        if(e.getSource() == reference_book_button)
        {
            CatagoryReference.setVisible(true);
            TextBookReference.setVisible(false);
            WorkbookReference.setVisible(false);
            NovelReference.setVisible(false);

            reference_book_button.setStyle("-fx-background-color: #727475;");
            textbook_button.setStyle("-fx-background-color: transparent;");
            workbook_button.setStyle("-fx-background-color: transparent;");
            novel_button.setStyle("-fx-background-color: transparent;");

        }
        else if(e.getSource() == textbook_button)
        {
            CatagoryReference.setVisible(false);
            TextBookReference.setVisible(true);
            WorkbookReference.setVisible(false);
            NovelReference.setVisible(false);

            reference_book_button.setStyle("-fx-background-color: transparent;");
            textbook_button.setStyle("-fx-background-color: #727475;");
            workbook_button.setStyle("-fx-background-color: transparent;");
            novel_button.setStyle("-fx-background-color: transparent;");
        }
        else if(e.getSource() == workbook_button)
        {
            CatagoryReference.setVisible(false);
            TextBookReference.setVisible(false);
            WorkbookReference.setVisible(true);
            NovelReference.setVisible(false);

            reference_book_button.setStyle("-fx-background-color: transparent;");
            textbook_button.setStyle("-fx-background-color: transparent;");
            workbook_button.setStyle("-fx-background-color: #727475;");
            novel_button.setStyle("-fx-background-color: transparent;");
        }
        else if(e.getSource() == novel_button)
        {
            CatagoryReference.setVisible(false);
            TextBookReference.setVisible(false);
            WorkbookReference.setVisible(false);
            NovelReference.setVisible(true);

            reference_book_button.setStyle("-fx-background-color: transparent;");
            textbook_button.setStyle("-fx-background-color: transparent;");
            workbook_button.setStyle("-fx-background-color: transparent;");
            novel_button.setStyle("-fx-background-color: #727475;");
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

    private List<Book> catagory_textbook_Add() throws SQLException {
        List<Book> ls = new ArrayList<>();
        Connection conn = mysqlconnect.ConnectDb();
        if (conn == null)
        {
            System.out.println("Connection is NULL !!!!");
        }
        assert conn != null;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM jdbc.textbook");
        while(rs.next())
        {
            Book book = new Book();
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setImageSrc(rs.getString("image_src"));
            ls.add(book);
        }

        return ls;
    }
    private List<Book> catagory_Add(String target) throws SQLException {
        List<Book> ls = new ArrayList<>();
        Connection conn = mysqlconnect.ConnectDb();
        if (conn == null)
        {
            System.out.println("Connection is NULL !!!!");
        }
        assert conn != null;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM jdbc." + target);
        while(rs.next())
        {
            Book book = new Book();
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setImageSrc(rs.getString("image_src"));
            ls.add(book);
        }

        return ls;
    }

}
