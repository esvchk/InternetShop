<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <h1>Product List</h1>
<body>
<h2>User List</h2>
<a href="AddProduct">Add New Product</a>
<br/><br/>
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
        List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
        if (products != null && !products.isEmpty()) {
            for (ProductDTO product : products) {
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
            <a href="?action=delete&id=<%= product.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">No users found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
