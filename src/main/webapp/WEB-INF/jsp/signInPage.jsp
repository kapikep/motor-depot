<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>

<html>
<head>
</head>
<body>
<h2><fmt:message bundle="${loc}" key="welcome.signIn"/></h2>
<form action="welcome" method="get">
    <input type="hidden" name="command" value="SignIn">
    Login: <input type="text" name="login"/> <br>
    Password: <input type="password" name="password" value="" size="16"/> <br>
    <input type="checkbox" name="remember" checked><label>Remember role</label> <br>
    <input type="submit" value="Sign in"/>
    <br>
    <c:out value="${sessionScope.access}" />
    <p> <a href="/motor-depot" > Go to main page </a> </p>
</form>
</body>
</html>