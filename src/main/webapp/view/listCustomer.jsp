<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel='shortcut icon' href='https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/399907868_122095451318111239_5994386970970505493_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=5f2048&_nc_ohc=YbnaucGG0lMQ7kNvgHN-SE3&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfCHf_jThCvQyGDA2FIbLrzXfojZ7QDLm_VCjjavb_ZrxA&oe=6640BD52'/>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/dataTables.bootstrap5.min.css" />

    <link rel="stylesheet" href="/css/style.css">
    <title>Tour List</title>
</head>
<body>

<section id="header">
    <a href=""><img src="/img/logovivu.png" alt=""></a>
    <div>
        <ul class="navbar">
            <li><a href="/">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="/customer">CUSTOMER</a></li>
            <li><a href="">Tour</a></li>
        </ul>
    </div>
</section>

<section id="table">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 mb-3">
                <div class="card">
                    <div class="card-header">
                        <span><i class="bi bi-table me-2"></i></span> Tour List
<%--                        <button onclick="window.location.href='/tours?action=create'" class="btn btn-primary">Create new tour</button>--%>
                        <%--                        <a href="/tours?action=create">Add New Tour</a>--%>
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="example" class="table table-striped data-table" style="width: 100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Code</th>
                                    <th>Destination</th>
                                    <th>Price</th>
                                    <th>Image</th>
                                    <th>Type</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tour" items="${listTourC}">
                                    <tr>
                                        <td><c:out value="${tour.id}"/></td>
                                        <td><c:out value="${tour.code}"/></td>
                                        <td><c:out value="${tour.destination}"/></td>
                                        <td><c:out value="${tour.price}"/></td>
                                        <td>
                                            <img src="${tour.img}"/>
                                        </td>
                                        <td>${tour.type.typeName}</td>
                                        <td>

<%--                                            <button type="button" class="btn btn-info" onclick="window.location.href='/?action=edit&id=${tour.getId()}'">Edit</button>--%>
                                            <button type="button" class="btn btn-info" onclick="window.location.href='/customer?action=choose&id=${tour.getId()}'">ODER TOUR</button>

                                                <%--                                            <a href="/tours?action=edit&id=${tour.id}">Edit</a>--%>
                                                <%--                                            <a href="/tours?action=delete&id=${tour.id}">Delete</a>--%>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Code</th>
                                    <th>Destination</th>
                                    <th>Price</th>
                                    <th>Image</th>
                                    <th>Type</th>
                                    <th>Action</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>

</body>
</html>
