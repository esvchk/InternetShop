<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.academy.course.service.ProductService" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2026
  Time: 00:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search by Id</title>
    <form action = "FindProductById" method = "post">
        Identifier: <input name = "id" type = "number"/><br/>
        <input type = "submit">
    </form>

    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Info</th>
            <th>Manufacturer</th>
            <th>Best Before</th>
        </tr>
        </thead>
        <tbody>
        <%
            ProductDTO product = (ProductDTO) request.getAttribute("product");
            if (product != null && product.getId() != null) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getInfo() %></td>
            <td><%= product.getManufacturer() %></td>
            <td><%= product.getBestBefore() %></td>
            <td>
                <a href="UpdateProduct?id=<%= product.getId() %>">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="DeleteProduct?id=<%= product.getId() %>">Delete</a>
            </td>
        </tr>
        <%
        } else {
        %>
        <tr>
            <td colspan="5">No products found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>



</head>
<body>

</body>
</html>
