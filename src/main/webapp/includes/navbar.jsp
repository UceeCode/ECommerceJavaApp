<%@ page import="com.example.ecommercejava.model.User" %><%--
  Created by IntelliJ IDEA.
  User: uchenna
  Date: 27/08/2024
  Time: 11:51 am
  To change this template use File | Settings | File Templates.
--%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" >
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
                    <a class="nav-link" href="index.jsp">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cart.jsp">Cart</a>
                </li>
                <%
                    if(auth != null){%>
                        <li class="nav-item">
                            <a class="nav-link " href="orders.jsp">Orders</a>
                        </li>

                <%} else {%>


                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signup.jsp">SignUp</a>
                </li>
                <%}
                %>
            </ul>

        </div>
    </div>
</nav>
</body>
</html>
