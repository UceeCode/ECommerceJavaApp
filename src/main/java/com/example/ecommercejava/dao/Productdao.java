package com.example.ecommercejava.dao;

import com.example.ecommercejava.model.Cart;
import com.example.ecommercejava.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Productdao {
    private Connection connection;

    public Productdao(Connection connection) {
        this.connection = connection;
    }

    // Fetch all products from the database
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
            System.out.println("Products retrieved: " + products.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Fetch products added to the cart based on cartList
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> Productcarts = new ArrayList<>();

        if (cartList != null && !cartList.isEmpty()) {
            String query = "SELECT * FROM products WHERE id = ?";
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
                for (Cart item : cartList) {
                    preparedStatement.setInt(1, item.getId());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            Cart row = new Cart();
                            row.setId(resultSet.getInt("id"));
                            row.setProductname(resultSet.getString("productname"));
                            row.setOrderDate(resultSet.getString("order_date"));
                            row.setPrice(String.valueOf(resultSet.getDouble("price") * item.getQuantity()));
                            row.setQuantity(item.getQuantity());
                            Productcarts.add(row);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Productcarts;
    }
}
