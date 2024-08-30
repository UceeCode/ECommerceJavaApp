package com.example.ecommercejava.dao;

import com.example.ecommercejava.model.Signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signupdao {
    public int register(Signup signup) {
        String insertquery = "INSERT INTO user (email, password) VALUES (?, ?)";
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");

            try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ECOMMERCEDB", "root", "Uche2006")){
                System.out.println("Database connection successful!");

                try(PreparedStatement preparedStatement = connection.prepareStatement(insertquery)){

                    preparedStatement.setString(1, signup.getEmail());
                    preparedStatement.setString(2, signup.getPassword());

                    System.out.println("Executing SQL query: " + preparedStatement);

                    result = preparedStatement.executeUpdate();

                    System.out.println("Rows affected: " + result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return result;
    }
}
