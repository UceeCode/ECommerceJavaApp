package com.example.ecommercejava.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("MySQL JDBC Driver Registered!");

                // Initialize the connection
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ECOMMERCEDB", "root", "Uche2006");
                System.out.println("Connection established successfully!");

            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found:");
                e.printStackTrace();
                throw e; // Rethrow the exception after logging
            } catch (SQLException e) {
                System.err.println("Connection Failed:");
                e.printStackTrace();
                throw e; // Rethrow the exception after logging
            }
        }
        return connection;
    }
}
