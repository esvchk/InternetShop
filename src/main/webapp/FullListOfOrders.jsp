<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.06.2026
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>Orders List</h1>

<a href="AddProduct.jsp">Add New Product</a>
<br/>
<a href="FindProductById.jsp">Search by Id</a>
<br/>
<a href="FindProductByName.jsp">Search by Name</a>
<br/><br/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Payment Data</th>
        <th>Total Cost</th>
        <th>IsBought</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${orders.isEmpty()}">
        <c:out value="Empty List of Orders"/>
    </c:if>
    <c:forEach
            var="order" items="${orders}">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.paymentData}"/></td>
            <td><c:out value="${order.totalCost}"/></td>
            <td><c:out value="${order.isBought}"/></td>
            <td><a href="${pageContext.request.contextPath}/DeleteOrder?id=${order.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="HelloPage.jsp">Home</a>
</body>
</html>

