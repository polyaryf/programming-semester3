<%@ page import="model.Cat" %><%--
  Created by IntelliJ IDEA.
  User: polinom
  Date: 20.09.2022
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: peachpuff";>
    <%
        Cat cat = (Cat) request.getAttribute("catForJsp");
    %>
    <hr>
    <hr>
    <hr>
    <h1 style="text-align:center;"><%=cat.sayHello()%></h1>

</body>
</html>
