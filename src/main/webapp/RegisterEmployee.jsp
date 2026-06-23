
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <form action = "RegisterEmployee" method = "post">
        Login: <input name = "login" type = "text" required/><br/>
        Password: <input name = "passWord" type = "text" required/><br/>
        Role: <input name = "role" type = "text" required/><br/>
        <input type = "submit">
    </form>

    <a href="AuthorizationForm.jsp">Authorization</a>
</head>
<body>
<a href="HelloPage.jsp">Home</a>
</body>
</html>
