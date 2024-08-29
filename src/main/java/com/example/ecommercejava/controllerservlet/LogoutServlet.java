package com.example.ecommercejava.controllerservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            // Check if the user is logged in
            if (request.getSession().getAttribute("user") != null) {
                // Invalidate the session to log out
                request.getSession().invalidate();
                // Redirect to the login page
                response.sendRedirect("login.jsp");
            } else {
                // If user is not logged in, redirect to the home page
                response.sendRedirect("index.jsp");
            }
        }
    }
}

