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
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Tour
                </h2>
            </caption>
            <c:if test="${tour != null}">
                <input type="hidden" name="id" value="<c:out value='${tour.id}' />"/>
            </c:if>
            <tr>
                <th>Tour Code:</th>
                <td>
                    <input type="text" name="code" size="45"
                           value="<c:out value='${tour.code}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Tour Destination:</th>
                <td>
                    <input type="text" name="destination" size="45"
                           value="<c:out value='${tour.destination}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>
                    <input type="text" name="price" size="15"
                           value="<c:out value='${tour.price}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Image</th>
                <td>
                    <input type="file" name="img" id="img" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Type</th>
                <td>
                    <select id="type" name="type">
                        <c:forEach items="${t_types}" var="t">
                            <option value="${t.typeId}">${t.typeName}</option>
                        </c:forEach>
                    </select>
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