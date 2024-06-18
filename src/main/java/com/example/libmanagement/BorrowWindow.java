package com.example.libmanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.xml.stream.events.StartElement;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BorrowWindow {
    @FXML
    private Label amount;

    @FXML
    private Label author;

    @FXML
    private Label bookname;

    @FXML
    private TextField returndate;

    public static boolean isValidDate(String dateStr) {
        // Biểu thức chính quy cho định dạng ngày tháng
        String regex = "^(\\d{2})/(\\d{2})/(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateStr);

        // Kiểm tra định dạng bằng regex
        if (!matcher.matches()) {
            return false;
        }

        // Định dạng ngày tháng
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Chuyển đổi chuỗi thành LocalDate
            LocalDate inputDate = LocalDate.parse(dateStr, dateFormatter);
            // Lấy ngày hiện tại
            LocalDate currentDate = LocalDate.now();

            // Kiểm tra nếu ngày nhập vào trước hoặc bằng ngày hiện tại
            if (!inputDate.isAfter(currentDate)) {
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public void setData(String bookname_v, String author_v) throws SQLException {
        bookname.setText(bookname_v);
        author.setText(author_v);
        Connection conn = mysqlconnect.ConnectDb();

        assert conn != null;
        Statement statement = conn.createStatement();
        String image_src = "";

        ResultSet rs = statement.executeQuery(SQLCommand("novel"));
        if(!rs.next())
        {
            ResultSet rs1 = statement.executeQuery(SQLCommand("textbook"));
            if(!rs1.next())
            {
                ResultSet rs2 = statement.executeQuery(SQLCommand("workbook"));
                if(!rs2.next())
                {
                    ResultSet rs3 = statement.executeQuery(SQLCommand("reference_book"));
                    if(!rs3.next())
                    {
                        System.out.println("Hết cứu");
                    }
                    else
                    {
                        amount.setText("Amount: " + rs3.getString("amount"));
                    }
                }
                else
                {
                    amount.setText("Amount: " + rs2.getString("amount"));
                }
            }
            else
            {
                amount.setText("Amount: " + rs1.getString("amount"));
            }
        }
        else {
            amount.setText("Amount: " + rs.getString("amount"));
        }
    }
    public String SQLCommand(String target_v)
    {
        System.out.println("bookname: " + bookname.getText());
        String result;
        result =  "SELECT amount from jdbc." +target_v.trim() + " WHERE name = '"+bookname.getText()+"'";
        System.out.println("result: [" + result + "]");
        return result;
    }
    public String BorrowSQLCommand(String target_v)
    {
        System.out.println("bookname: " + bookname.getText());
        String result;
        result =  "UPDATE jdbc." +target_v.trim() + " SET amount = amount - 1   WHERE name = '"+bookname.getText()+"'";
        System.out.println("result: [" + result + "]");
        return result;
    }
    public void BorrowAction(ActionEvent event) throws SQLException {
        System.out.println("bookname: " + bookname.getText());
        System.out.println("author: " + author.getText());
        String return_date = returndate.getText();
        if(!isValidDate(return_date))
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);

            warningAlert.setTitle("Cảnh báo");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Ngày tháng không hợp lệ!");
            warningAlert.showAndWait();
        }
        else {
            System.out.println("return date: " + return_date);
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);


            Connection conn = mysqlconnect.ConnectDb();
            assert conn != null;
            Statement statement = conn.createStatement();
            Statement statement1 = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQLCommand("novel"));
            if(!rs.next())
            {
                ResultSet rs1 = statement.executeQuery(SQLCommand("textbook"));
                if(!rs1.next())
                {
                    ResultSet rs2 = statement.executeQuery(SQLCommand("workbook"));
                    if(!rs2.next())
                    {
                        ResultSet rs3 = statement.executeQuery(SQLCommand("reference_book"));
                        if(!rs3.next())
                        {
                            System.out.println("Hết cứu");
                        }
                        else
                        {
                            //amount.setText("Amount: " + rs3.getString("amount"));
                            statement1.executeUpdate(BorrowSQLCommand("reference_book"));
                        }
                    }
                    else
                    {
                        //amount.setText("Amount: " + rs2.getString("amount"));
                        statement1.executeUpdate(BorrowSQLCommand("work_book"));
                    }
                }
                else
                {
                    //amount.setText("Amount: " + rs1.getString("amount"));
                    statement1.executeUpdate(BorrowSQLCommand("textbook"));
                }
            }
            else {
                //amount.setText("Amount: " + rs.getString("amount"));
                statement1.executeUpdate(BorrowSQLCommand("novel"));
            }

            // Thiết lập tiêu đề cho thông báo
            infoAlert.setTitle("Thông báo");

            // Thiết lập tiêu đề đầu không có nội dung (null)
            infoAlert.setHeaderText(null);

            // Thiết lập nội dung chính của thông báo
            infoAlert.setContentText("Gửi yêu cầu thành công!");
            infoAlert.showAndWait();

        }


    }

}
