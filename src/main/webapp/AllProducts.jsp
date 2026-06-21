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
        </tr>
    </c:forEach>
    </tbody>
    <c:if test="${currentPage > 1 }">
        <a href="${pageContext.request.contextPath}/ShowProducts?currentPage=${currentPage - 1}">Back</a>
    </c:if>


    <c:set var="startPage" value="${currentPage - 2}"/>
    <c:if test="${startPage < 1}">
        <c:set var="startPage" value="1"/>
    </c:if>

    <c:set var="endPage" value="${currentPage + 2}"/>
    <c:if test="${endPage > lastPage}">
        <c:set var="endPage" value="${lastPage}"/>
    </c:if>

    <c:forEach var="i" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <span>
                    <b>${i}</b>
                </span>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/ShowProducts?currentPage=${i}"> ${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < lastPage}">
        <a href="${pageContext.request.contextPath}/ShowProducts?currentPage=${currentPage + 1}">Next</a>
    </c:if>

</table>
<c:forEach var="quantityOfRecords" begin="${5}" step="${5}" end="${30}">
    <a href="${pageContext.request.contextPath}/ShowProducts?pageSize=${quantityOfRecords}">${quantityOfRecords}</a>
</c:forEach>


</body>
</html>
