<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
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
            <td>
                <a href="UpdateProduct?id=<%= product.getId() %>">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="DeleteProduct?id=<%= product.getId() %>">Delete</a>
            </td>
        </tr>
        <%
            }
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
