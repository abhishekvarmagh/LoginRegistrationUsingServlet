<%--
  Created by IntelliJ IDEA.
  User: abhishek verma
  Date: 4/8/2020
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success Page</title>
</head>
<body>
<h1>Hi <%= request.getAttribute("user") %>, Login Successful</h1>
<a href="login.html">Login Page</a>
</body>
</html>
