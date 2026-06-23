<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
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
            <th>IsAvailable</th>
            <th>Limit</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${products != null}">
        <c:out value="Product not found"/>
        </c:if>

        <c:forEach  var="product" items="${products}">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><c:out value="${product.info}"/></td>
                <td><c:out value="${product.isAvailable}"/></td>
                <td><c:out value="${product.productLimit}"/></td>
                <td>
                    <a href="UpdateProduct?id=${product.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="DeleteProduct?id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</head>
<body>
<a href="HelloPage.jsp">Home</a>
</body>
</html>
