<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h2>Sign in</h2>
<jsp:include page="/WEB-INF/jsp/_localization.jsp"></jsp:include>
<form action="welcome" method="get">
    <input type="hidden" name="command" value="SignIn">
    Login: <input type="text" name="login"/> <br>
    Password: <input type="password" name="password" value=""/> <br>
    <input type="checkbox" name="remember" checked><label>Remember role</label> <br>
    <input type="submit" value="Sign in"/>
    <br>
    <c:out value="${sessionScope.access}" />
    <p> <a href="/motor-depot" > Go to main page </a> </p>
</form>
</body>
</html>