<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.06.2026
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <c:set var="productVar" value="${product}"/>
    <c:if test="${product == null}">
        <c:out value="Product not found"/>
    </c:if>
    <head>
        <title>Set limit on Product</title>
    </head>
<body>
<h2>Set Limit</h2>
<form action="SetLimit" method="post">
    <input type="hidden" name="id" value="${productVar.id}">
    Limit: <input type="number" name="limit"
                  value="${productVar.productLimit}"><br>
    <input type="submit">
</form>
<br>
<a href="ShowProducts">Back to Product List</a>

</body>

</html>
