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
import java.util.logging.Logger;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());
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
            LOGGER.info("Retrieved Products: " + products.size());
            request.setAttribute("products", products);
            request.getRequestDispatcher("/orders.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.severe("Database error: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Unexpected error: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage());
        }
    }
}
