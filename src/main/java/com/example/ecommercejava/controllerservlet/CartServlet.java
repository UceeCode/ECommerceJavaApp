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

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart_list");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        if (action != null && (action.equals("increaseQuantity") || action.equals("decreaseQuantity"))) {
            updateCartQuantity(cartList, id, action);
        } else {
            addProductToCart(cartList, id);
        }

        session.setAttribute("cart_list", cartList);
        resp.sendRedirect("cart.jsp");
    }

    private void updateCartQuantity(ArrayList<Cart> cartList, int id, String action) {
        for (Cart cartItem : cartList) {
            if (cartItem.getId() == id) {
                if (action.equals("increaseQuantity")) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                } else if (action.equals("decreaseQuantity") && cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
                break;
            }
        }
    }

    private void addProductToCart(ArrayList<Cart> cartList, int id) {
        try (Connection connection = DBconnection.getConnection()) {
            Productdao productDao = new Productdao(connection);
            List<Product> products = productDao.getAllProducts();

            Product product = null;
            for (Product p : products) {
                if (p.getId() == id) {
                    product = p;
                    break;
                }
            }

            if (product != null) {
                boolean productExistsInCart = false;

                for (Cart cartItem : cartList) {
                    if (cartItem.getId() == id) {
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        productExistsInCart = true;
                        break;
                    }
                }

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

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
