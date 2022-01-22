<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<body>
	<p> <a href="welcome?command=GoToSignIn"> Sign In </a> </p>
	<c:if test="${sessionScope.role != null}">
		<p> <a href="welcome?command=LogOut"> Log Out </a> </p>
	</c:if>
	Role ${sessionScope.role}
	<h2>Minsk Motor depot</h2>
	<p> <a href="presentation"> Our cars </a> </p>

	<p>Our contacts +375291111111</p>
<p></p>
</body>
</html>
