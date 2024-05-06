<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 5/5/2024
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>Bạn có muốn xóa tour này không?</h1>
    <form action="" method="post">
        <table border="1">
            <tr>
                <td>ID</td>
                <td value=""><c:out value ='${listdelete.id}'/></td>
            </tr>
            <tr>
                <td>CODE</td>
                <td value=""><c:out value ='${listdelete.code}'/></td>
            </tr>
            <tr>
                <td>DESTINATION</td>
                <td value=""><c:out value ='${listdelete.destination}'/></td>
            </tr>
            <tr>
                <td>PRICE</td>
                <td value=""><c:out value ='${listdelete.price}'/></td>
            </tr>
            <tr>
                <td>TYPE</td>
                <td value=""><c:out value ='${listdelete.type.typeName}'/></td>
            </tr>
            <tr>
                <td colspan="2" ><img src="${listdelete.img}" width="200" height="200"></td>
            </tr>
        </table>
        <button>DELETE</button>
    </form>
</div>
</body>
</html>
