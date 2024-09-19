package com.example.ecommercejava.controllerservlet;

import com.example.ecommercejava.model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "RemoveFromCart", value = "/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the product to be removed from the cart
        String id = request.getParameter("id");

        if (id != null) {
            // Get the session
            HttpSession session = request.getSession();

            // Get the cart list from the session
            List<Cart> cart_List = (List<Cart>) session.getAttribute("cart_list");

            if (cart_List != null) {
                // Use Iterator to safely remove an item while iterating
                Iterator<Cart> iterator = cart_List.iterator();
                while (iterator.hasNext()) {
                    Cart cartItem = iterator.next();
                    if (cartItem.getId()==Integer.parseInt(id)) {
                        iterator.remove(); // Safely remove the item
                        break;
                    }
                }
            }
            // Update the session with the new cart list
            session.setAttribute("cart_list", cart_List);
        }

        // Redirect back to the cart page
        response.sendRedirect("cart.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call doGet to handle POST requests
        doGet(request, response);
    }
}
