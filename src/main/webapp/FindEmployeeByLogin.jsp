<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search by Login</title>
    <form action = "FindEmployeeByLogin" method = "post">
        Login: <input name = "login" type = "text"/><br/>
        <input type = "submit">
    </form>

    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="worker" value="${employee}"/>
        <c:if test="${employee == null}">
            <c:out value="Employee not found"/>
        </c:if>
            <tr>
                <td><c:out value="${worker.id}"/></td>
                <td><c:out value="${worker.login}"/></td>
                <td><c:out value="${worker.role}"/></td>
                <td>
                    <a href="UpdateEmployee?id=${worker.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="DeleteEmployee?id=${worker.id}">Delete</a>
                </td>
            </tr>
        </tbody>
    </table>
    <a href="HelloPage.jsp">Home</a>
</head>
</html>
