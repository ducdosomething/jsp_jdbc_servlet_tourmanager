<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tour Management Application</title>
</head>
<body>
<center>
    <h1>Tour Management</h1>
    <h2>
        <a href="tours?action=tours">List All Tours</a>
    </h2>
</center>
<div align="center">
    <form method="post" action="UploadFileServlet" enctype="multipart/form-data">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Tour</h2>
            </caption>
            <tr>
                <th>Tour ID:</th>
                <td>
                    <input type="text" name="id" id="id" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Tour Code:</th>
                <td>
                    <input type="text" name="code" id="code" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Tour Destination:</th>
                <td>
                    <input type="text" name="destination" id="destination" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>
                    <input type="text" name="price" id="price" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Image</th>
                <td>
                    <input type="file" name="img" id="img" size="15"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>