
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search by Id</title>
    <form action = "FindProductById" method = "post">
        Identifier: <input name = "id" type = "number"/><br/>
        <input type = "submit">
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
       <c:set var="product" value="${product}"/>
       <c:if test="${product != null}">
           <c:out value="Product not found"/>
           </c:if>
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
        </tbody>
    </table>
    <a href="HelloPage.jsp">Home</a>
</head>
</html>
