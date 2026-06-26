<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.06.2026
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Current order</title>

    <c:set var="order" value="${productsAndOrder.orderDTO}" scope="request"/>
    <br>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Info</th>
            <th>Limit</th>
            <th>Quantity</th>
            <th>Discount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${order.itemsDTO}">
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.productDTO.name}"/></td>
            <td><c:out value="${item.productDTO.price}"/></td>
            <td><c:out value="${item.productDTO.info}"/></td>
            <td><c:out value="${item.productDTO.productLimit}"/></td>
            <td><c:out value="${item.quantity}"/></td>
            <td><c:out value="${item.discount}"/></td>
        </c:forEach>
        </tbody>
    </table>
</head>


<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Info</th>
        <th>Limit</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="products" value="${productsAndOrder.products}"/>

    <c:forEach var="product" items="${products}">
        <tr>
        <td><c:out value="${product.id}"/></td>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td><c:out value="${product.info}"/></td>
        <td><c:out value="${product.productLimit}"/></td>
        <td><c:out value="${product.categoryDTO}"/></td>
    </c:forEach>
    </tr>
    </tbody>

</table>
<body>

</body>
</html>
