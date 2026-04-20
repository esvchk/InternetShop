<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <h1>Product List</h1>
<body>
<ul>
    <%
        List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
        for (ProductDTO product : products) { %>
    <li><%= product %>
    </li>
    <%}%>

</ul>

</body>
</html>
