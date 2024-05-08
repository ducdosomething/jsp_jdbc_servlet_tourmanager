<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 5/7/2024
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/thankyou.css">
    <title>Title</title>
</head>
<body>

<h3>Cám ơn quý khách hàng</h3>
<h3>Mọi thắc mắc hay đóng góp vui lòng liên hệ sđt: 02343003006</h3>
<button class="btn btn-primary" onclick="goToCustomerList()">Quay lại trang</button>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    function goToCustomerList() {
        window.location.href = "/customer";
    }
</script>
</body>
</html>
