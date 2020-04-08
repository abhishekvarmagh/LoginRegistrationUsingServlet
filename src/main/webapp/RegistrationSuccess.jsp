<%--
  Created by IntelliJ IDEA.
  User: abhishek verma
  Date: 4/8/2020
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Successful</title>
</head>
<body>
<h1>Hi <%= session.getAttribute("username") %>, Registration Successful</h1>
<a href="login.html">Login Page</a>
</body>
</html>
