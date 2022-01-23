<%--
  Created by IntelliJ IDEA.
  User: kep
  Date: 23.01.2022
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create cars Page</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
Create car
<form action="welcome" method="get">
    <input type="hidden" name="command" value="SignIn">
    Login: <input type="text" name="login" value=""/> <br>
    Password: <input type="password" name="password" value=""/> <br>
    <input type="checkbox" name="remember" checked><label>Remember role</label> <br>
    <input type="submit" value="Sign in"/>
    <br>
    <c:out value="${sessionScope.access}" />
    <p> <a href="/motor-depot" > Go to main page </a> </p>

</form>
</body>
</html>
