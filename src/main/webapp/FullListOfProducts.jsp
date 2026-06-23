<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.06.2026
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Product List</h1>

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
        <th>Name</th>
        <th>Price</th>
        <th>Info</th>
        <th>IsAvailable</th>
        <th>Limit</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${products.isEmpty()}">
        <c:out value="Empty List of Products"/>
    </c:if>
    <c:forEach
            var="product" items="${products}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.price}"/></td>
            <td><c:out value="${product.info}"/></td>
            <td><c:out value="${product.isAvailable}"/></td>
            <td><c:out value="${product.productLimit}"/></td>
            <td><a href="${pageContext.request.contextPath}/UpdateProduct?id=${product.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/DeleteProduct?id=${product.id}">Delete</a></td>
            <td><a href="${pageContext.request.contextPath}/SetLimit?id=${product.id}">Set Limit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="HelloPage.jsp">Home</a>
</body>
</html>
