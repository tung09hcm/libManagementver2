package com.example.libmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.xml.stream.events.StartElement;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BorrowWindow implements Initializable {
    @FXML
    private Label amount;

    @FXML
    private Label author;

    @FXML
    private Label bookname;

    @FXML
    private TextField returndate;
    @FXML
    private ImageView image_v;

    public void setData(String bookname_v, String author_v)
    {
        bookname.setText(bookname_v);
        author.setText(author_v);
    }
    public String SQLCommand(String target_v)
    {
        return "SELECT image_src from jdbc." +target_v.trim() + "WHERE name = '"+ bookname.getText() +"'";
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("say hi from borrowWindow");
        Connection conn = mysqlconnect.ConnectDb();
        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(SQLCommand("novel"));
            if(!rs.next())
            {
                ResultSet rs1 = statement.executeQuery(SQLCommand("reference_book"));
                if(!rs1.next())
                {
                    ResultSet rs2 = statement.executeQuery(SQLCommand("textbook"));
                    if(!rs2.next())
                    {
                        ResultSet rs3 = statement.executeQuery(SQLCommand("workbook"));
                        if(!rs3.next())
                        {
                            System.out.println("Hết cứu");
                        }
                        else
                        {
                            image_v.setImage(new Image(rs3.getString("image_src")));
                        }
                    }
                    else
                    {
                        image_v.setImage(new Image(rs2.getString("image_src")));
                    }
                }
                else
                {
                    image_v.setImage(new Image(rs1.getString("image_src")));
                }
            }
            else {
                image_v.setImage(new Image(rs.getString("image_src")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
