<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h2>Sign in</h2>
<form action="welcome" method="post">
    <input type="hidden" name="command" value="SignIn">
    Login: <input type="text" name="login" value=""/> <br>
    Password: <input type="password" name="password" value=""/> <br>
    <input type="checkbox" name="remember"><label>Remember Me</label> <br>
    <input type="submit" value="Sign in"/>
</form>
</body>
</html>