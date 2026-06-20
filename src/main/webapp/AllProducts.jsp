<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Product List</h1>

<a href="AddProduct.jsp">Add New Product</a>
<a href="FindProductById.jsp">Search by Id</a>
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
    <c:forEach
            var="product" items="${products}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.price}"/></td>
            <td><c:out value="${product.isAvailable}"/></td>
            <td><c:out value="${product.info}"/></td>
            <td><c:out value="${product.productLimit}"/></td>
        </tr>
    </c:forEach>
    
    <c:if test="${currentPage != lastPage}">
        <a href="${}">Edit</a>
    </c:if>

    </tbody>
</table>
</body>
</html>
