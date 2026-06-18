<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <h1>Product List</h1>
<body>
<a href="AddProduct.jsp">Add New Product</a>
<a href="FindProductById.jsp">Search by Id</a>
<a href="FindProductByName.jsp">Search by Name</a>
<br/><br/>
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
        Set<ProductDTO> products = (Set<ProductDTO>) request.getAttribute("products");
        if (products != null && !products.isEmpty()) {
            for (ProductDTO product : products) {
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

            <a href="AddProductInOrder?id=<%= product.getId() %>">Add</a>
            &nbsp;&nbsp;&nbsp;
            <a href="DeleteProduct?id=<%= product.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">No Products found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
