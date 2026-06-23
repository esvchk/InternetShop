<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.06.2026
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update Employee</title>
    <c:set var="worker" value="${employee}"/>
    <c:if test="${employee == null}">
        <c:out value="Employee not found"/>
    </c:if>
    <br><br>

<body>
<h2>Edit Employee</h2>
<form action="UpdateEmployee" method="post">
    <input type="hidden" name="id" value="${worker.id}">
    Login: <input type="text" name="login"
                  value="${worker.login}" required><br>
    Role: <input type="text"  name="role"
                 value="${worker.role}" required><br>
    <input type="submit">
</form>
<a href="HelloPage.jsp">Home</a>
<br>
<%--<a href="ShowProducts">Back to Product List</a>--%>

<%--<a href="ShowProducts">Back to Product List</a>--%>

</body>

</html>
