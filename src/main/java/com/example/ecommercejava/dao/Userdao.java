package com.example.ecommercejava.dao;

import com.example.ecommercejava.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Userdao {
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Userdao(Connection connection) {
        this.connection = connection;
    }

    public User login(String email, String password) {
        User user = null;
        try {
            query = "SELECT * FROM user WHERE email = ? AND password = ?";
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
