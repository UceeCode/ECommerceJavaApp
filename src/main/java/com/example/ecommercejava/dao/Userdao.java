package com.example.ecommercejava.dao;

import com.example.ecommercejava.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Userdao {
    private Connection connection;

    public Userdao(Connection connection) {
        this.connection = connection;
    }

    public User login(String email, String password) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
