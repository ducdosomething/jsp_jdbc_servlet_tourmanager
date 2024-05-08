<%@ page import="org.example.tourscrud.model.Tour" %>
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

<script src="./js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/jquery.dataTables.min.js"></script>
<script src="./js/dataTables.bootstrap5.min.js"></script>
<script src="./js/script.js"></script>
<%
    Tour tour1 = (Tour) request.getAttribute("tour");
%>
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

        // xml blob res
        function getImgURL(url, callback){
            var xhr = new XMLHttpRequest();
            xhr.onload = function() {
                callback(xhr.response);
            };
            xhr.open('GET', url);
            xhr.responseType = 'blob';
            xhr.send();
        }

        function loadURLToInputField(url){
            getImgURL(url, (imgBlob)=>{
                // Load img blob to input
                const parts = url.split('/');
                const fileName = parts[parts.length - 1];
                // let fileName = 'hasFilename.jpg' // should .replace(/[/\\?%*:|"<>]/g, '-') for remove special char like / \
                let file = new File([imgBlob], fileName,{type:"image/jpeg", lastModified:new Date().getTime()}, 'utf-8');
                let container = new DataTransfer();
                container.items.add(file);
                document.querySelector('#img').files = container.files;
                // document.querySelector('#status').files = container.files;

            })
        }


        window.onload = function(e) {

            var imgTour = "<%= tour1.getImg() %>";

            console.log(imgTour)
            loadURLToInputField(imgTour)
        }
    </script>
</body>
</html>