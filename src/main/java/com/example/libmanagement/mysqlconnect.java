package com.example.libmanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlconnect {
    public static Connection ConnectDb() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root", "401190");
            if (conn == null) {
                System.out.println("Connection is null");
            } else {
                System.out.println("Successfully connected to MySQL database");
            }
            return conn;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An unexpected error occurred");
            return null;
        }
    }

}
