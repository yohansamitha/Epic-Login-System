<%--
  Created by IntelliJ IDEA.
  User: yohan
  Date: 8/22/2021
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<section class="main-header">
    <section class="container">
        <div>
            <h1>Login</h1>
            <hr/>
            <h3>Welcome To Login Management System</h3>
            <input id="txtUserName" name="txtUserName" placeholder="Username" type="text">
            <input id="txtPassword" name="txtPassword" placeholder="Password" type="password">
            <br>
            <button id="btnLogin" type="button">Login</button>
            <br>
        </div>
    </section>
</section>
<script src="assets/css/jquery-3.6.0.min.js"></script>
<script>

    $("#btnLogin").click(function () {
        let txtUserName = $("#txtUserName").val();
        let txtPassword = $("#txtPassword").val();
        console.log(txtUserName, txtPassword);

        function clear() {
            $('#txtUserName').val("");
            $('#txtPassword').val("");
        }

        if (txtUserName !== "" && txtPassword !== "") {
            $.ajax({
                url: 'loginServlet',
                method: 'GET',
                async: true,
                contentType: "application/json",
                dataType: "json",
                headers: {
                    "txtUserName": txtUserName,
                    "txtPassword": txtPassword
                },
                success: function (data, responseState, xhr) {
                    console.log(data);
                    console.log(data.status);
                    if (data.status === "200") {
                        window.location.href = "http://localhost:8080/epl/Dashboard.jsp";
                    } else {
                        clear();
                        alert(data.message);
                    }
                }
            });
        } else {
            alert("Please enter username and password")
        }
    });
</script>
</body>
</html>
