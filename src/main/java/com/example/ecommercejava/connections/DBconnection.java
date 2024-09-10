package com.example.ecommercejava.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBconnection {
    private static final Logger LOGGER = Logger.getLogger(DBconnection.class.getName());
    private static final String URL = "jdbc:mysql://localhost:3306/ECOMMERCEDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Uche2006";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            LOGGER.info("MySQL JDBC Driver Registered!");

            // Initialize the connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Connection established successfully!");
            return connection;
        } catch (ClassNotFoundException e) {
            LOGGER.severe("MySQL JDBC Driver not found:");
            e.printStackTrace();
            throw e; // Rethrow the exception after logging
        } catch (SQLException e) {
            LOGGER.severe("Connection Failed:");
            e.printStackTrace();
            throw e; // Rethrow the exception after logging
        }
    }
}
