<%@ page import="com.academy.course.dto.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2026
  Time: 00:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form action = "FindProductById" method = "post">
        Identifier: <input name = "id" type = "number"/><br/>
        <input type = "submit">
    </form>
    <%
        ProductDTO productDTO = (ProductDTO) request.getAttribute("product");
        if (productDTO != null) {%>
    <%= productDTO %>
        <%}%>


</head>
<body>

</body>
</html>
