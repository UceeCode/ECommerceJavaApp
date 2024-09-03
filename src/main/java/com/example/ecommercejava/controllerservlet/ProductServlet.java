package com.example.ecommercejava.controllerservlet;

import com.example.ecommercejava.dao.Productdao;
import com.example.ecommercejava.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ECOMMERCEDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Uche2006";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection()) {
            Productdao productDao = new Productdao(connection);
            List<Product> products = productDao.getAllProducts();
            System.out.println("Retrieved Products: " + products.size()); // Debugging line
            request.setAttribute("products", products);
            request.getRequestDispatcher("/orders.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage());
        }
    }

}
