<%@ page import="com.academy.course.dto.OrderDTO" %>
<html>
<head>
    <title>Search by Id</title>
    <form action = "FindOrderById" method = "post">
        Identifier: <input name = "id" type = "number"/><br/>
        <input type = "submit">
    </form>

    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>CustomerID</th>

        </tr>
        </thead>
        <tbody>
        <%
            OrderDTO orderDTO = (OrderDTO) request.getAttribute("order");
            if (orderDTO != null && orderDTO.getId() != null) {
        %>
        <tr>
            <td><%= orderDTO.getId() %></td>
            <td><%= orderDTO.getCustomer() %></td>
            <td>
                <a href="UpdateOrder?id=<%= orderDTO.getId() %>">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="DeleteOrder?id=<%= orderDTO.getId() %>">Delete</a>
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

