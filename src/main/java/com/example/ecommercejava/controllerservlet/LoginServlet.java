package com.example.ecommercejava.controllerservlet;

import com.example.ecommercejava.connections.DBconnection;
import com.example.ecommercejava.dao.Userdao;
import com.example.ecommercejava.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("txt/html");

        try (PrintWriter out = resp.getWriter()) {
            String email = req.getParameter("login-email");
            String password = req.getParameter("login-password");

            try {
                Userdao userdao = new Userdao(DBconnection.getConnection());
                User user = userdao.login(email, password);
                if (user != null) {
                    // Successful login
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
                } else {
                    // Unsuccessful login
                    out.println("Invalid email or password. Please try again.");
                    req.getRequestDispatcher("login.jsp").include(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
