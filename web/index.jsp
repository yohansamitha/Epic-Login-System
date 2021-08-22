<%--
  Created by IntelliJ IDEA.
  User: yohan
  Date: 8/22/2021
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<section class="main-header">
    <section class="container">
        <form action="login" method="post">
            <h1>Login</h1>
            <hr/>
            <h3>Welcome To Login Management System</h3>
            <input id="txtUserName" name="txtUserName" placeholder="Username" type="text">
            <input id="txtPassword" name="txtPassword" placeholder="Password" type="password">
            <br>
            <button type="submit">Login</button>
            <br>
        </form>
    </section>
</section>
</body>
</html>
