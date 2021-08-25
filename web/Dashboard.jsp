<%--
  Created by IntelliJ IDEA.
  User: yohan
  Date: 8/22/2021
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="assets/css/bootstrap/css/bootstrap.min.css">
</head>
<body>
<%
    if (session.getAttribute("user") == null)
        response.sendRedirect("index.jsp");
%>
<section class="container-fluid">
    <article class="row mb-2 mt-2">
        <div class="row h-100">
            <div class="col-12 col-sm-1 col-md-1 col-lg-1 col-xl-1 col-xxl-1 ">
                <button id="btnLogout" class="btn btn-danger btn-sm">Logout</button>
            </div>
            <div class="col-12 col-sm-1 col-md-1 col-lg-1 col-xl-1 col-xxl-1">
                <h5>UserName</h5>
            </div>
            <div class="col ">
                <h5>${user.getName()}</h5>
            </div>
        </div>
    </article>
    <section class="row">
        <article class="col-12 col-sm-12 col-md-12 col-lg-5 col-xl-5 col-xxl-5">
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="userName" placeholder="Leanne Graham">
                <label for="userName">Full Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="userAddress"
                       placeholder="Kulas Light, Apt. 556, Gwenborough">
                <label for="userAddress">Address</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control " id="phoneNumber" placeholder="+94-71 66 55 698">
                <label for="phoneNumber">Phone No</label>
            </div>
            <div class="form-floating mb-3">
                <input type="email" class="form-control " id="emailAddress" placeholder="leannegraham@gmail.com">
                <label for="emailAddress">Email address</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control " id="password" placeholder="************">
                <label for="emailAddress">Password</label>
            </div>
        </article>
        <article class="col" id="userTable">
            <div class="border" style="height: 355px; overflow: auto;">
                <table class="table table-hover">
                    <thead class="bg-dark text-white">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">username</th>
                        <th scope="col">address</th>
                        <th scope="col">contact</th>
                        <th scope="col">email</th>
                    </tr>
                    </thead>
                    <tbody id="tblUserDetail">
                    <%--<tr>
                        <th scope="row">I001</th>
                        <td>Mark</td>
                        <td>panadura</td>
                        <td>554454654</td>
                        <td>Mark@gmail.com</td>
                    </tr>--%>
                    </tbody>
                </table>
            </div>
        </article>
        <section class="row mt-2 mb-3">
            <article class="col">
                <button id="btnSaveUser" type="button" class="btn btn-primary mt-1">Save User</button>
                <button id="btnUpdateUser" type="button" class="btn btn-success mt-1">Update User</button>
                <button id="btnDeleteUser" type="button" class="btn btn-danger mt-1">Delete User</button>
                <button id="btnCancel" type="button" class="btn btn-secondary mt-1">Cancel</button>
            </article>
        </section>
    </section>
</section>
<script src="assets/css/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/css/jquery-3.6.0.min.js"></script>
<script>
    let selectedUserId;

    function loadAllUsers() {
        console.log("testing");
        clear();
        $.ajax({
            url: 'userManageServlet',
            method: 'GET',
            async: true,
            dataType: "json",
            success: function (data, responseState, xhr) {
                console.log(data);
                console.log(data.data);
                if (responseState === 'success') {
                    $('#tblUserDetail>tr').off('click');
                    $('#tblUserDetail').empty();
                    for (let user of data.data) {
                        let row = "<tr>" +
                            "<td>" + user.id + "</td>" +
                            "<td>" + user.name + "</td>" +
                            "<td>" + user.address + "</td>" +
                            "<td>" + user.contact + "</td>" +
                            "<td>" + user.emailAddress + "</td>>" +
                            "</tr>";
                        $('#tblUserDetail').append(row);
                    }
                    $('#tblUserDetail>tr').click(function () {
                        selectedUserId = parseInt($(this).children('td:eq(0)').text());
                        $('#userName').val($(this).children('td:eq(1)').text());
                        $('#userAddress').val($(this).children('td:eq(2)').text());
                        $('#phoneNumber').val($(this).children('td:eq(3)').text());
                        $('#emailAddress').val($(this).children('td:eq(4)').text());
                        console.log(selectedUserId, "selected user id");
                        console.log(typeof selectedUserId, "selected user id type");
                    });
                }
            }
        });
    }

    loadAllUsers();

    $("#btnSaveUser").click(function () {
        let userName = $("#userName").val();
        let userAddress = $("#userAddress").val();
        let phoneNumber = $("#phoneNumber").val();
        let emailAddress = $("#emailAddress").val();
        let password = $("#password").val();
        console.log(userName, userAddress, phoneNumber, emailAddress, password);

        if (userName !== "" && userAddress !== "" && phoneNumber !== "" && emailAddress !== "" && password !== "") {
            $.ajax({
                url: 'userManageServlet',
                method: 'POST',
                async: true,
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    userName: userName,
                    userAddress: userAddress,
                    phoneNumber: phoneNumber,
                    emailAddress: emailAddress,
                    password: password
                }),
                success: function (data, responseState, xhr) {
                    console.log(data);

                }
            });
        } else {
            alert("Please Enter Valid Information");
        }
    });

    $("#btnUpdateUser").click(function () {
        let userName = $("#userName").val();
        let userAddress = $("#userAddress").val();
        let phoneNumber = $("#phoneNumber").val();
        let emailAddress = $("#emailAddress").val();
        let password = $("#password").val();
        console.log(userName, userAddress, phoneNumber, emailAddress, password);

        if (selectedUserId !== undefined && userName !== "" && userAddress !== "" && phoneNumber !== "" && emailAddress !== "") {
            $.ajax({
                url: 'userManageServlet',
                method: 'PUT',
                async: true,
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    id: selectedUserId,
                    userName: userName,
                    userAddress: userAddress,
                    phoneNumber: phoneNumber,
                    emailAddress: emailAddress,
                    password: password
                }),
                success: function (data, responseState, xhr) {
                    console.log(data);
                }
            });
        } else {
            alert("Please Enter Valid Information");
        }
    });

    $("#btnDeleteUser").click(function () {
        console.log(selectedUserId, "asdasdasdz")
        if (selectedUserId !== undefined) {
            $.ajax({
                url: 'userManageServlet?id=' + selectedUserId,
                method: 'DELETE',
                async: true,
                dataType: "json",
                success: function (data, responseState, xhr) {
                    console.log(data);
                }
            });
        } else {
            alert("Please Enter Valid Information");
        }
    });

    function clear() {
        $("#userName").val("");
        $("#userAddress").val("");
        $("#phoneNumber").val("");
        $("#emailAddress").val("");
        $("#password").val("");
        selectedUserId = undefined;
        // loadAllUsers();
        console.log(selectedUserId, "selected user id");
    }

    $("#btnCancel").click(function () {
        clear()
    });

    $("#btnLogout").click(function () {
        $.ajax({
            url: 'loginServlet',
            method: 'DELETE',
            async: true,
            success: function (data, responseState, xhr) {
                console.log(data);
                if (responseState === 'success') {
                    window.location.reload();
                }
            }
        });
    });

</script>
</body>
</html>
