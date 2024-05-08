<%@ page import="org.example.tourscrud.model.Admin" %>
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
    <a href="/"><img src="/img/logovivu.png" alt=""></a>
    <div>
        <ul class="navbar">
            <li><a href="/">Home</a></li>
            <li><a href="viewcustomer?action=quanity">Quanity view</a></li>
            <%--<<<<<<< HEAD--%>
            <%--            <li><a href="customer">Blog</a></li>--%>
            <%--            <li><a href="tours">Tour</a></li>--%>
            <%--=======--%>
            <li><a href="viewcustomer">ViewCustomer</a></li>
            <%--            <li>Hi <%--%>
            <%--                Admin admin = (Admin)session.getAttribute("username");--%>
            <%--                out.print(admin.getName());--%>
            <%--            %></li>--%>
            <li><a href="logout">Sign Out</a></li>
            <%-->>>>>>> bfbf90f3f52fdeae7a0ea06ec45de026c37f631a--%>
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

                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="example" class="table table-striped data-table" style="width: 100%">
                                <thead>
                                <tr>
                                    <th>CODE</th>
                                    <th>CUSTOMER NAME</th>
                                    <th>ADDRESS</th>
                                    <th>PHONE</th>
                                    <th>MEMBER</th>
                                    <th>PRICE</th>
                                    <th>TOTAL</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="oder" items="${listoder}">
                                    <tr>
                                        <td><c:out value="${oder.codeOD}"/></td>
                                        <td><c:out value="${oder.nameOD}"/></td>
                                        <td><c:out value="${oder.addOD}"/></td>
                                        <td><c:out value="${oder.phoneOD}"/></td>
                                        <td><c:out value="${oder.memberOD}"/></td>
                                        <td><c:out value="${oder.price}"/></td>
                                        <td><c:out value="${oder.total}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
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
