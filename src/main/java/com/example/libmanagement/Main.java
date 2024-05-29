package com.example.libmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.UUID;



public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = mysqlconnect.ConnectDb();
        assert conn != null;
        Statement statement = conn.createStatement();
        String src_img = "toan_";
        String book_name = "Toán ";
        String author = "NHÀ XUẤT BẢN GIÁO DỤC VIỆT NAM";
        int amount = 50;
        for(int i = 1; i<= 5 ; i++)
        {
            for(int j = 1; j<=2; j++)
            {
                String uniqueID = UUID.randomUUID().toString();
                String book_name_src = book_name + String.valueOf(i) + " tập " + String.valueOf(j);
                String image_src ="/workbook/"+ src_img + String.valueOf(i) + "_" + String.valueOf(j) + ".png";
                statement.executeUpdate("INSERT INTO jdbc.workbook" +
                        "(id,name,author,image_src,amount)" +
                        "VALUES ('"+ uniqueID +"', '"+ book_name_src +"', '"+ author +"', '"+ image_src +"', '"+ String.valueOf(amount) +"');");
            }
        }
//        for (int i = 4; i <= 5; i++)
//        {
//            String uniqueID = UUID.randomUUID().toString();
//            String book_name_src = book_name + String.valueOf(i);
//            String image_src ="/textbook/"+ src_img + String.valueOf(i) + ".png";
//            statement.executeUpdate("INSERT INTO jdbc.textbook" +
//                    "(id,name,author,image_src,amount)" +
//                    "VALUES ('"+ uniqueID +"', '"+ book_name_src +"', '"+ author +"', '"+ image_src +"', '"+ String.valueOf(amount) +"');");
//        }

    }
}
