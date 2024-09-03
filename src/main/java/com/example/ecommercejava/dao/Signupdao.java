package com.example.ecommercejava.dao;

import com.example.ecommercejava.model.Signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signupdao {
    private static final String URL = "jdbc:mysql://localhost:3306/ECOMMERCEDB?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Uche2006";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int register(Signup signup) {
        String insertQuery = "INSERT INTO user (email, password) VALUES (?, ?)";
        int result = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, signup.getEmail());
            preparedStatement.setString(2, signup.getPassword());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
