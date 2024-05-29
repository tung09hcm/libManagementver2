package com.example.libmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetData {
    public static void main(String[] args) throws SQLException {

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
            System.out.println("id: " + rs.getString("name"));
        }
    }
}
