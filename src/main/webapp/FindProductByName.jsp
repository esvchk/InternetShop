<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find By Name</title>

    <form action="FindProductByName" method="post">
        Name: <input name="name" type="text"/><br/>
        <input type="submit">
    </form>

    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Info</th>
            <th>IsAvailable</th>
            <th>Limit</th>
        </tr>
        </thead>
        <tbody>
        <%
            ProductDTO product = (ProductDTO) request.getAttribute("products");
            if (product != null && product.getId() != null) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getInfo() %></td>
            <td><%= product.getIsAvailable() %></td>
            <td><%= product.getProductLimit() %></td>
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
            <td colspan="5">No operators found</td>
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
