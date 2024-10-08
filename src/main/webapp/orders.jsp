<%@ page import="com.example.ecommercejava.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommercejava.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Check user authentication


    // Fetch the list of products from request
    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // Redirect to login page if the user is not logged in
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders - Surulere Market</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">TODAY'S MARKET</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cart.jsp">Cart</a>
                </li>
                <li class="nav-item">
                <li class="nav-item"><a class="nav-link" href="ProductServlet">Products</a></li>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signup.jsp">SignUp</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2>WELCOME TO SURULERE MARKET</h2>
    <div class="card header my-3">All Products</div>

    <div class="row">
        <% if (products != null && !products.isEmpty()) { %>
        <% for (Product p : products) { %>
        <div class="col-md-4">
            <div class="card mb-4">
                <img class="card-img-top" src="<%= request.getContextPath() + "/images/" + p.getImage() %>" alt="Product Image">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getProductname() %></h5>
                    <p class="card-text">Price: $<%= p.getPrice() %></p>
                    <p class="card-text">Order Date: <%= p.getOrderDate() %></p>
                    <a href="CartServlet?id=<%= p.getId() %>" class="btn btn-primary">ADD TO CART</a>
                </div>
            </div>
        </div>
        <% } %>
        <% } else { %>
        <p>No products found.</p>
        <% } %>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
