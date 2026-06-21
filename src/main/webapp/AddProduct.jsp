<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.04.2026
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <form action = "AddProduct" method = "post">
        Name: <input name = "name" type = "text" required/><br/>
        Price: <input name = "price" step="0.01" type = "number" required/><br/>
        Info: <input name = "info" type = "text"/><br/>
        IsAvailable:
        <input type="radio" name="isAvailable" value="true" required> Yes
        <input type="radio" name="isAvailable" value="false" required> No<br>
        <input type = "submit">
    </form>
</head>
<body>

</body>
</html>
