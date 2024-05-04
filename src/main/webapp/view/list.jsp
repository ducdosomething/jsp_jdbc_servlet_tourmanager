<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="/css/list.css">
    <title>Tour Management Application</title>
</head>
<body>
<center>
    <h1>Tour Management</h1>
    <h2>
        <a href="/tours?action=create">Add New Tour</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Tours</h2></caption>
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Destination</th>
            <th>Price</th>
            <th>Image</th>
            <th>Type</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="tour" items="${listTour}">
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
                    <a href="/tours?action=edit&id=${tour.id}">Edit</a>
                    <a href="/tours?action=delete&id=${tour.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
