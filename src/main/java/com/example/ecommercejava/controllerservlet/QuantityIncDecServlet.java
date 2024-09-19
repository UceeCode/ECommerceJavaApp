package com.example.ecommercejava.controllerservlet;

import com.example.ecommercejava.model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuantityIncDecServlet", value = "/QuantityIncDecServlet")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action type (increase or decrease) and the cart item ID
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        // Retrieve the cart list from the session
        List<Cart> cart_List = (List<Cart>) request.getSession().getAttribute("cart_list");

        if (cart_List != null) {
            // Find the cart item by its ID
            for (Cart c : cart_List) {
                if (c.getId() == id) {
                    // Perform the appropriate action
                    if (action != null && action.equals("increaseQuantity")) {
                        // Increase quantity
                        c.setQuantity(c.getQuantity() + 1);
                    } else if (action != null && action.equals("decreaseQuantity")) {
                        // Decrease quantity only if greater than 1
                        if (c.getQuantity() > 1) {
                            c.setQuantity(c.getQuantity() - 1);
                        }
                    }
                    break; // Break after the match is found and action is performed
                }
            }
        }

        // Redirect back to the cart page after updating quantity
        response.sendRedirect("cart.jsp");
    }
}
