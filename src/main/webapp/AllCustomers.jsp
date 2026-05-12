<%@ page import="com.academy.course.dto.CustomerDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
    <h1>Customer List</h1>
<body>
<a href="AddProduct.jsp">Add New Product</a>
<a href="FindProductById.jsp">Search by Id</a>
<a href="FindProductByName.jsp">Search by Name</a>
<br/><br/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Email</th>
        <th>DateTimeOfRegistration</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customers");
        if (customers != null && !customers.isEmpty()) {
            for (CustomerDTO customer : customers) {
    %>
    <tr>
        <td><%= customer.getId() %></td>
        <td><%= customer.getLogin() %></td>
        <td><%= customer.getEmail() %></td>
        <td>
            <a href="UpdateProduct?id=<%= customer.getId() %>">Edit</a>
            &nbsp;&nbsp;&nbsp;
            <a href="DeleteProduct?id=<%= customer.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">No Customers found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
