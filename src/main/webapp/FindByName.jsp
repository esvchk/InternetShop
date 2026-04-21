<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2026
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <form action="FindProductByName" method="post">
        Name: <input name="name" type="text"/><br/>
        <input type="submit">
    </form>

    <ul>
        <%
            List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
            if (products != null) {
                for (ProductDTO product : products) { %>
        <li><%= product %>
        </li>
            <%}%>
        <%}%>
    </ul>
</head>
<body>

</body>
</html>
