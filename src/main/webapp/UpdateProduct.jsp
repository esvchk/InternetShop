<%@ page import="com.academy.course.dto.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2026
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update Product</title>
    <c:set var="productVar" value="${product}"/>
    <c:if test="${product == null}">
    <c:out value="Product not found"/>
    </c:if>
    <head>
        <title>Edit Product</title>
    </head>
<body>
<h2>Edit Product</h2>
<form action="UpdateProduct" method="post">
    <input type="hidden" name="id" value="${productVar.id}">
    Name: <input type="text" name="name"
                 value="${productVar.name}" required><br>
    Price: <input type="number" step="0.01" name="price"
                  value="${productVar.price}" required><br>
    Info: <input type="text" name="info"
                 value="${productVar.info}" ><br>
    IsAvailable: <input type="radio" name="isAvailable" value="true" required> Yes
    <input type="radio" name="isAvailable" value="false" required > No <br>
    Limit: <input type="number" name="limit"
                  value="${productVar.productLimit}"><br>
    <input type="submit">
</form>
<br>
<a href="ShowProducts">Back to Product List</a>

<a href="ShowProducts">Back to Product List</a>

<a href="HelloPage.jsp">Home</a>
</body>

</html>
