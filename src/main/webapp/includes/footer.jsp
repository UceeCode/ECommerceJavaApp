<%@ page import="com.example.ecommercejava.model.User" %><%--
  Created by IntelliJ IDEA.
  User: uchenna
  Date: 27/08/2024
  Time: 11:51 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
<head>
    <title>Title</title>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
