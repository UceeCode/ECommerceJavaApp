<%@ page import="com.example.ecommercejava.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommercejava.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Get the cart list from the session
    List<Cart> cart_List = (List<Cart>) session.getAttribute("cart_list");

    // Get the logged-in user from the session
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
    <title>Cart - Surulere Market</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">TODAY'S MARKET</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart</a></li>
                <li class="nav-item"><a class="nav-link" href="ProductServlet">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                <li class="nav-item"><a class="nav-link" href="signup.jsp">SignUp</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="d-flex py-3">
        <h3>Total Price: $<%
            double totalPrice = 0;
            if (cart_List != null) {
                for (Cart item : cart_List) {
                    totalPrice += Double.parseDouble(item.getPrice()) * item.getQuantity();
                }
            }
            out.print(totalPrice);
        %></h3>
        <a class="mx-3 btn btn-primary" href="#">Check Out</a>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Order Date</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Remove</th>
        </tr>
        </thead>
        <tbody>
        <% if (cart_List != null && !cart_List.isEmpty()) {
            for (Cart c : cart_List) {
        %>
        <tr>
            <td><%= c.getProductname() %></td>
            <td><%= c.getOrderDate() %></td>
            <td>$<%= c.getPrice() %></td>
            <td>
                <form action="QuantityIncDecServlet" method="get" class="form-inline">
                    <input type="hidden" name="id" value="<%= c.getId() %>">
                    <div class="form-group d-flex justify-content-between align-items-center">
                        <!-- Decrease Button -->
                        <a class="btn btn-sm btn-decre" href="CartServlet?action=decreaseQuantity&id=<%= c.getId() %>">
                            <i class="fas fa-minus-square"></i>
                        </a>

                        <!-- Quantity Input Field -->
                        <input type="text" name="quantity" class="form-control mx-2" value="<%= c.getQuantity() %>" readonly>

                        <!-- Increase Button -->
                        <a class="btn btn-sm btn-incre" href="CartServlet?action=increaseQuantity&id=<%= c.getId() %>">
                            <i class="fas fa-plus-square"></i>
                        </a>
                    </div>
                </form>
            </td>
            <td><a class="btn btn-sm btn-danger" href="RemoveFromCart?id=<%= c.getId() %>">Remove</a></td>
        </tr>
        <% } } else { %>
        <tr>
            <td colspan="5">Your cart is empty.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
