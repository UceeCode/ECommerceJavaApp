package com.example.ecommercejava.dao;

import com.example.ecommercejava.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Productdao {
    private Connection connection;

    public Productdao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductname(resultSet.getString("productname"));
                product.setPrice(resultSet.getString("price"));
                product.setImage(resultSet.getString("image"));
                product.setOrderDate(resultSet.getString("order_date"));
                products.add(product);
            }
            System.out.println("Products retrieved: " + products.size()); // Debugging line

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}

