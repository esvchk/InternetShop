<%@ page import="com.academy.course.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.academy.course.dto.EmployeeDTO" %>
<%@ page import="java.util.Set" %>
<%@ page import="jdk.jshell.spi.ExecutionControl" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.05.2026
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <h1>Orders List</h1>
<body>
<a href="CreateOrder">Add New Order</a>
<a href="FindProductById.jsp">Search by Id</a>
<a href="FindProductByName.jsp">Search by Name</a>
<br/><br/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>DateTimeOfCreation</th>
        <th>DateTimeOfPurchasing</th>
    </tr>
    </thead>
    <tbody>
    <%
        Set<OrderDTO> orders = (Set<OrderDTO>) session.getAttribute("orders");
        if (orders != null && !orders.isEmpty()) {
            for (OrderDTO order : orders) {
    %>
    <tr>
        <td><%= order.getId() %></td>
        <td>
            <a href="UpdateOrder?id=<%= order.getId() %>">Edit</a>
            &nbsp;&nbsp;&nbsp;
            <a href="DeleteOrder?id=<%= order.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">No Orders found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
