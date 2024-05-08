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
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel='shortcut icon' href='https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/399907868_122095451318111239_5994386970970505493_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=5f2048&_nc_ohc=YbnaucGG0lMQ7kNvgHN-SE3&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfCHf_jThCvQyGDA2FIbLrzXfojZ7QDLm_VCjjavb_ZrxA&oe=6640BD52'/>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="css/dataTables.bootstrap5.min.css" />
    <link rel="stylesheet" href="/css/delete.css">
    <title>Title</title>
</head>
<body>
<div class="my-container">
        <h1>Tour detail</h1>
        <form action="" method="post" class="text-center">
            <table border="1" class="table">
                <tr>
                    <td style="width: 70%">ID</td>
                    <td style="width: 30%;" value=""><c:out value ='${listdelete.id}'/></td>
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
                <tr>
                    <td colspan="2">
                        <button class="btn btn-danger" >DELETE</button>
                    </td>
                </tr>
            </table>
        </form>
        <button class="btn btn-secondary" onclick="goToTourList()">BACK</button>
</div>

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>

<script>
    function goToTourList() {
        window.location.href = "/tours";
    }
</script>

</body>
</html>
