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
    <link rel="stylesheet" href="/css/edit.css">
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
    <form class="mx-auto" method="post">
        <div class="text-center"><h1>Edit tour</h1></div>
        <div>
            <c:if test="${tour != null}">
                <input type="hidden" name="id" value="<c:out value='${tour.id}' />"/>
            </c:if>
        </div>
        <div>
            <label for="code" class="form-label">Code</label>
            <input type="text" class="form-control" id="code" name="code" value="<c:out value='${tour.code}' />">
        </div>
        <div>
            <label for="destination" class="form-label">Destination</label>
            <input type="text" class="form-control" id="destination" name="destination" value="<c:out value='${tour.destination}' />">
        </div>
        <div>
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price" value="<c:out value='${tour.price}' />">
        </div>
        <div>
            <label for="img" class="form-label">Image</label>
            <input type="file" class="form-control" name="img" id="img" size="15" onchange="handleInputImgChange()"/>
            <div>
                <img id="imgTour" src="${tour.img}" />
            </div>
        </div>
        <div>
            <label for="type" class="form-label">Type</label>
            <select class="form-control" id="type" name="type">
                <c:forEach items="${t_types}" var="t">
                    <option value="${t.typeId}"
                            <c:if test="${t.typeId == tour.type.typeId}">
                                selected
                            </c:if>
                    >${t.typeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="button">
            <button type="submit" class="btn btn-primary mt-4" value="Update tour">Update tour</button>
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
<%--    <form method="post">--%>
<%--        <table border="1" cellpadding="5">--%>
<%--            <c:if test="${tour != null}">--%>
<%--                <input type="hidden" name="id" value="<c:out value='${tour.id}' />"/>--%>
<%--            </c:if>--%>
<%--            <tr>--%>
<%--                <th>Tour Code:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="code" size="45"--%>
<%--                           value="<c:out value='${tour.code}' />"--%>
<%--                    />--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Tour Destination:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="destination" size="45"--%>
<%--                           value="<c:out value='${tour.destination}' />"--%>
<%--                    />--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Price:</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="price" size="15"--%>
<%--                           value="<c:out value='${tour.price}' />"--%>
<%--                    />--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Image</th>--%>
<%--                <td>--%>
<%--                    <input type="file" name="img" id="img" size="15" onchange="handleInputImgChange()"/>--%>
<%--                    <div>--%>
<%--                        <img id="imgTour" src="${tour.img}" />--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <th>Type</th>--%>
<%--                <td>--%>
<%--                    <select id="type" name="type">--%>
<%--                        <c:forEach items="${t_types}" var="t">--%>
<%--                            <option value="${t.typeId}"--%>
<%--                            <c:if test="${t.typeId == tour.type.typeId}">--%>
<%--                                selected--%>
<%--                            </c:if>--%>
<%--                            >${t.typeName}</option>--%>
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

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>

    <script>
        function handleInputImgChange(){
            let imgInp = document.getElementById("img");
            let imgTour = document.getElementById("imgTour")
            const [file] = imgInp.files
            if (file) {
                imgTour.src = URL.createObjectURL(file)
            }
        }

        function goToTourList() {
            window.location.href = "/tours";
        }
    </script>
</body>
</html>