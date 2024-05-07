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
    <link rel="stylesheet" href="/css/create.css">
    <title>Tour Management Application</title>
</head>
<body>

<section id="header">
    <a href=""><img src="/img/logovivu.png" alt=""></a>
    <div>
        <ul class="navbar">
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Blog</a></li>
            <li><a href="tours">Tour</a></li>
        </ul>
    </div>
</section>

<div class="container">
    <form class="mx-auto" enctype='multipart/form-data' method="post">
        <h4 class="text-center">Tour information</h4>
        <div>
            <label for="id" class="form-label">ID</label>
            <input type="text" class="form-control" id="id" name="id">
        </div>
        <div>
            <label for="code" class="form-label">Code</label>
            <input type="text" class="form-control" id="code" name="code">
        </div>
        <div>
            <label for="destination" class="form-label">Destination</label>
            <input type="text" class="form-control" id="destination" name="destination">
        </div>
        <div>
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price">
        </div>
        <div>
            <label for="img" class="form-label">Image</label>
            <input type="file" class="form-control" name="img" id="img"/>
        </div>
        <div>
            <label for="type" class="form-label">Type</label>
            <select class="form-control" id="type" name="type">
                <c:forEach items="${tour}" var="t">
                    <option selected>Choose the tour type</option>
                    <option value="${t.typeId}">${t.typeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="button">
            <button type="submit" class="btn btn-primary mt-4" value="Create tour">Create tour</button>
            <button type="button" class="btn btn-dark mt-4" onclick="goToTourList()">Back to tour list</button>
        </div>

        <div class="show-message">
            <p>
                <c:if test='${requestScope["message"] != null}'>
                    <span class="message">${requestScope["message"]}</span>
                </c:if>
            </p>
        </div>
    </form>
</div>


<%--<div align="center">--%>
<%--    <form method="post" enctype="multipart/form-data">--%>
<%--        <table border="1" cellpadding="5">--%>
<%--            <tr>--%>
<%--                <th>Tour ID:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="id" id="id" size="45"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Tour Code:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="code" id="code" size="45"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Tour Destination:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="destination" id="destination" size="45"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Price:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="price" id="price" size="15"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Image</th>--%>
<%--                <td>--%>
<%--                    <input type="file" name="img" id="img" size="15"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Type</th>--%>
<%--                <td>--%>
<%--                    <select id="type" name="type">--%>
<%--                        <c:forEach items="${t_types}" var="t">--%>
<%--                            <option value="${t.typeId}">${t.typeName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td colspan="2" align="center">--%>
<%--                    <input type="submit" value="Save"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>
<%--</div>--%>

<script>
    function goToTourList() {
        window.location.href = "/tours";
    }
</script>

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>
</body>
</html>