<%@ page import="com.example.ecommercejava.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null) {
        response.sendRedirect("index.jsp");
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
        <a class="navbar-brand" href="index.jsp">Surulere Market</a>
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
                    <a class="nav-link" href="orders.jsp">Orders</a>
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
    <h2>Your Orders</h2>
    <div class="row">
        <!-- Order Item -->
        <div class="col-md-4">
            <div class="card mb-4">
                <img class="card-img-top" src="path/to/product-image.jpg" alt="Product Image">
                <div class="card-body">
                    <h5 class="card-title">Product Name</h5>
                    <p class="card-text">Quantity: 2</p>
                    <p class="card-text">Price: $20.00</p>
                    <p class="card-text">Order Date: 2024-08-27</p>
                    <a href="#" class="btn btn-primary">View Details</a>
                </div>
            </div>
        </div>
        <!-- End of Order Item -->

        <!-- Repeat Order Items as needed -->
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
