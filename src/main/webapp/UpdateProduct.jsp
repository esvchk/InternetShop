<%@ page import="com.academy.course.dto.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2026
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update Product</title>
    <% ProductDTO productDTO = (ProductDTO) request.getAttribute("product"); %>
    <head>
        <title>Edit Product</title>
    </head>
    <body>
    <h2>Edit User</h2>
    <% if (productDTO != null) { %>
    <form action="UpdateProduct" method="post">
        <input type="hidden" name="id" value="<%= productDTO.getId() %>">
        Name: <input type="text" name="name"
                     value="<%= productDTO.getName() %>" required><br><br>
        Price: <input type="number" name="price"
                      value="<%= productDTO.getPrice() %>" required><br><br>
        Info: <input type="text" name="info"
                      value="<%= productDTO.getInfo() %>" required><br><br>
        Manufacturer: <input type="text" name="manufacturer"
                      value="<%= productDTO.getManufacturer() %>" required><br><br>
        Best Before: <input type="date" name="bestBefore"
                      value="<%= productDTO.getBestBefore() %>" required><br><br>
        <input type="submit" value="Update Product">
    </form>
    <br>
    <a href="ShowProducts">Back to User List</a>
    <% } else { %>
    <p>User not found!</p>
    <a href="ShowProducts">Back to User List</a>
    <% } %>
    </body>

</html>
