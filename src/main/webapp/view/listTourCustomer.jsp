<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/dataTables.bootstrap5.min.css" />

    <link rel="stylesheet" href="/css/style.css">
    <title>List OderTours</title>
</head>
<body>

<section id="header">
    <a href=""><img src="/img/logovivu.png" alt=""></a>
    <div>
        <ul class="navbar">
            <li><a href="/">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="customer">Customer</a></li>
            <li><a href="#">Tour</a></li>
        </ul>
    </div>
</section>

<div class="container">
    <form action="" method="post" class="form-control">
    <table class="table">
        <tr>
            <th colspan="5" class="title">Customer information</th>
        </tr>
        <tr>
            <td colspan="2">Name</td>
            <td colspan="3">${listcus.getName()} </td>
        </tr>
        <tr>
            <td colspan="2">Age</td>
            <td colspan="3"> ${listcus.getAge()}</td>
        </tr>
        <tr>
            <td colspan="2">Address</td>
            <td colspan="3"> ${listcus.getAddress()}</td>
        </tr>
        <tr>
            <td colspan="2">Phone number</td>
            <td colspan="3">${listcus.getPhone()} </td>
        </tr>
        <tr>
            <td>ID Tour</td>
            <td>Destination</td>
            <td>Price</td>
            <td>Number of People</td>
            <td>Total</td>
        </tr>
        <tr>
            <td>${listCustour.getId()}</td>
            <td>${listCustour.getDestination()}</td>
            <td>${listCustour.getPrice()}</td>
            <td>${listcus.getMember()}</td>
            <td>${listcus.getMember()}*${listCustour.getPrice()}</td>
        </tr>
    </table>
        <button type="button" class="btn btn-danger" onclick="window.location.href='/customer?action=delete&id=${listCustour.getId()}'">Hủy Bỏ</button>
        <button type="button" class="btn btn-danger" onclick="window.location.href='/customer?action=thankyou'">Xác nhận</button>
    </form>
</div>

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>

</body>
</html>
