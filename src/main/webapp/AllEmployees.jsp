<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
    <h1>Employee List</h1>
<body>
<a href="AddProduct.jsp">Add New Product</a>
<a href="FindProductById.jsp">Search by Id</a>
<a href="FindProductByName.jsp">Search by Name</a>
<br/><br/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${employees.isEmpty()}">
        <c:out value="Empty List of Employees"/>
    </c:if>
    <c:forEach
            var="employee" items="${employees}">
        <tr>
            <td><c:out value="${employee.id}"/></td>
            <td><c:out value="${employee.login}"/></td>
            <td><c:out value="${employee.role}"/></td>
            <td><a href="${pageContext.request.contextPath}/UpdateEmployee?id=${employee.id}">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/DeleteEmployee?id=${employee.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>

    <c:out value="Choose limit of elements on page"/>
    <br>
    <c:forEach var="number" begin="${5}" step="${5}" end="${20}">
        <c:choose>
            <c:when test="${number == pageSize}">
                <span> ${number}</span>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/ShowEmployees?currentPage=${currentPage}&pageSize=${number}"> ${number}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <br>
    <br>
    <br>

    <c:if test="${currentPage > 1 }">
        <a href="${pageContext.request.contextPath}/ShowEmployees?currentPage=${currentPage - 1}&pageSize=${pageSize}">Back</a>
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
                <a href="${pageContext.request.contextPath}/ShowCustomers?currentPage=${i}&pageSize=${pageSize}"> ${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < lastPage}">
        <a href="${pageContext.request.contextPath}/ShowCustomers?currentPage=${currentPage + 1}&pageSize=${pageSize}">Next</a>
    </c:if>

</table>
</body>
</html>
