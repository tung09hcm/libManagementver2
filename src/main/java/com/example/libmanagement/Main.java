package com.example.libmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.UUID;



public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = mysqlconnect.ConnectDb();
        String command = "SELECT image_src from jdbc.novel WHERE name = 'Angel next door 4'";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(command);
        if(!rs.next())
        {
            System.out.println("NULL DATA !!!");
            return;
        }
        while(rs.next())
        {
            System.out.println("tín hiệu");
        }
        System.out.println("end");
    }
}
