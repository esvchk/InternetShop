<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.06.2026
  Time: 21:10
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
    <th>Login</th>
    <th>Role</th>
  </tr>
  </thead>
  <tbody>
  <c:if test="${employees.isEmpty()}">
    <c:out value="Empty List of Employees"/>
  </c:if>
  <c:forEach
          var="worker" items="${employees}">
    <tr>
      <td><c:out value="${worker.id}"/></td>
      <td><c:out value="${worker.login}"/></td>
      <td><c:out value="${worker.role}"/></td>
      <td><a href="${pageContext.request.contextPath}/UpdateEmployee?id=${product.id}">Edit</a></td>
      <td><a href="${pageContext.request.contextPath}/DeleteEmployee?id=${product.id}">Delete</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="HelloPage.jsp">Home</a>
</body>
</html>
