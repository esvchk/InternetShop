<%@ page import="com.academy.course.dto.EmployeeDTO" %>
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
        List<EmployeeDTO> customers = (List<EmployeeDTO>) request.getAttribute("customers");
        if (customers != null && !customers.isEmpty()) {
            for (EmployeeDTO employee : customers) {
    %>
    <tr>
        <td><%= employee.getId() %></td>
        <td><%= employee.getLogin() %></td>
        <td><%= employee.getPaymentData() %></td>
        <td>
            <a href="UpdateProduct?id=<%= employee.getId() %>">Edit</a>
            &nbsp;&nbsp;&nbsp;
            <a href="DeleteProduct?id=<%= employee.getId() %>">Delete</a>
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
