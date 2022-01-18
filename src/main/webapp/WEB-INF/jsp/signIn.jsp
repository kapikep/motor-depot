<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Sign in</h2>
	<form action="Controller" method="get">
		<input type="hidden" name="command" value="GoToSignIn"> Login: <input
			type="text" name="login" value="" /> <br> Password: <input
			type="password" name="password" value="" /> <input type="submit"
			value="Press me" />
	</form>
</body>
</html>