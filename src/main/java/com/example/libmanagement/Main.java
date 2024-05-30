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
        String src_img = "anh";
        String book_name = "Từ điển Anh Việt";
        String author = "NHÀ XUẤT BẢN ĐẠI HỌC QUỐC GIA HÀ NỘI";
        int amount = 50;
//        for(int i = 1; i<= 5 ; i++)
//        {
//            for(int j = 1; j<=2; j++)
//            {
//                String uniqueID = UUID.randomUUID().toString();
//                String book_name_src = book_name + String.valueOf(i) + " tập " + String.valueOf(j);
//                String image_src ="/workbook/"+ src_img + String.valueOf(i) + "_" + String.valueOf(j) + ".png";
//                statement.executeUpdate("INSERT INTO jdbc.workbook" +
//                        "(id,name,author,image_src,amount)" +
//                        "VALUES ('"+ uniqueID +"', '"+ book_name_src +"', '"+ author +"', '"+ image_src +"', '"+ String.valueOf(amount) +"');");
//            }
//        }
        for (int i = 1; i <= 1; i++)
        {
            String uniqueID = UUID.randomUUID().toString();
            String book_name_src = book_name;
            String image_src ="/reference_book/"+ src_img + ".png";
            statement.executeUpdate("INSERT INTO jdbc.reference_book" +
                    "(id,name,author,image_src,amount)" +
                    "VALUES ('"+ uniqueID +"', '"+ book_name_src +"', '"+ author +"', '"+ image_src +"', '"+ String.valueOf(amount) +"');");
        }
//        String uniqueID = UUID.randomUUID().toString();
//        String book_name_src = "Angel next door 5";
//        author = "Saekisan";
//        String image_src ="/novel/"+ "angel5" + ".png";
//        statement.executeUpdate("INSERT INTO jdbc.novel" +
//                "(id,name,author,image_src,amount)" +
//                "VALUES ('"+ uniqueID +"', '"+ book_name_src +"', '"+ author +"', '"+ image_src +"', '"+ String.valueOf(amount) +"');");

    }
}
