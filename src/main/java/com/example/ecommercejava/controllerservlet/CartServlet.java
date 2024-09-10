package com.example.ecommercejava.controllerservlet;

import com.example.ecommercejava.dao.Productdao;
import com.example.ecommercejava.model.Cart;
import com.example.ecommercejava.model.Product;
import com.example.ecommercejava.connections.DBconnection;
import com.example.ecommercejava.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // Check if user is logged in
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Retrieve product ID from request parameter
        int id = Integer.parseInt(req.getParameter("id"));

        // Initialize cart list
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart_list");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        // Fetch product details from database
        try (Connection connection = DBconnection.getConnection()) {
            Productdao productDao = new Productdao(connection);
            List<Product> products = productDao.getAllProducts();

            // Find the product by ID
            Product product = null;
            for (Product p : products) {
                if (p.getId() == id) {
                    product = p;
                    break;
                }
            }

            // If product is found, add to cart
            if (product != null) {
                boolean productExistsInCart = false;

                // Check if product already exists in cart
                for (Cart cartItem : cartList) {
                    if (cartItem.getId() == id) {
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        productExistsInCart = true;
                        break;
                    }
                }

                // If product does not exist in cart, add it
                if (!productExistsInCart) {
                    Cart newCartItem = new Cart();
                    newCartItem.setId(product.getId());
                    newCartItem.setOrderDate(product.getOrderDate());
                    newCartItem.setProductname(product.getProductname());
                    newCartItem.setPrice(product.getPrice());
                    newCartItem.setQuantity(1);
                    cartList.add(newCartItem);
                }
            }

            // Update session attribute
            session.setAttribute("cart_list", cartList);

            // Redirect to cart page
            resp.sendRedirect("cart.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database connection error.");
        }
    }
}
