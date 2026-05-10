<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="com.academy.course.dto.OrderDTO" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.05.2026
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product in order</title>
        <% ProductDTO productDTO = (ProductDTO) request.getAttribute("product"); %>
        <% OrderDTO orderDTO = (OrderDTO) request.getAttribute("order"); %>
<body>
<h2>Edit Product</h2>
<% if (productDTO != null) { %>
<form action="AddProductInOrder" method="post">
    <input type="hidden" name="id" value="<%= productDTO.getId() %>">
    Name: <input type="email" name="name"
                 value="<%= productDTO.getName() %>" required><br><br>
    Price: <input type="number" step="0.01" name="price"
                  value="<%= productDTO.getPrice() %>" required><br><br>
    Info: <input type="text" name="info"
                 value="<%= productDTO.getInfo() %>" required><br><br>
    Manufacturer: <input type="text" name="manufacturer"
                         value="<%= productDTO.getManufacturer() %>" required><br><br>
    Best Before: <input type="date" name="bestBefore"
                        value="<%= productDTO.getBestBefore() %>" required><br><br>
    <input type="submit" value="Add product">
</form>
<br>
<a href="ShowProducts">Back to Product List</a>
<% } else { %>
<p>Product not found!</p>
<a href="ShowProducts">Back to Product List</a>
<% } %>
</body>

</html>
