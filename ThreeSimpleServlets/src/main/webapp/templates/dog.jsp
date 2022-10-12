<%@ page import="model.Dog" %><%--
  Created by IntelliJ IDEA.
  User: polinom
  Date: 21.09.2022
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: peachpuff";>
    <%
        Dog dog = (Dog) request.getAttribute("dogForJsp");
    %>
    <hr>
    <hr>
    <hr>
    <h1 style="text-align:center;"><%=dog.sayHello()%></h1>


</body>
</html>
