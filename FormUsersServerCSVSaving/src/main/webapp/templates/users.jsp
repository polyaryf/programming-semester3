<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: polinom
  Date: 12.10.2022
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <th>ID</th>
    <th>FIRST NAME</th>
    <th>LAST NAME</th>
    <th>COURSE NAME</th>
    <th>AGE</th>

    <%
        List<User> users = (List<User>) request.getAttribute("usersForJsp");
        for (int i = 0; i < users.size(); i++) {
    %>

    <tr>
        <td><%=users.get(i).getId()%>
        </td>
        <td><%=users.get(i).getFirstName()%>
        </td>
        <td><%=users.get(i).getLastName()%>
        </td>
        <td><%=users.get(i).getCourseName()%>
        </td>
        <td><%=users.get(i).getAge()%>
        </td>
    </tr>

    <% } %>
</table>
<a href="/user/add">ADD USER</a>

</body>
</html>