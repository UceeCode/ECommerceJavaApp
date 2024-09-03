package com.example.ecommercejava.controllerservlet;

import com.example.ecommercejava.dao.Signupdao;
import com.example.ecommercejava.model.Signup;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("signup-email");
        String password = req.getParameter("signup-password");

        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Please fill all the fields");
            getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
        } else {
            Signup signup = new Signup();
            signup.setEmail(email);
            signup.setPassword(password);

            Signupdao signupdao = new Signupdao();

            int result = signupdao.register(signup);

            if (result > 0) {
                req.setAttribute("success", "Signup success");
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Invalid email or password");
                getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
            }
            getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}