<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<body>
<form method="get">
</form>
	<a href="welcome?command=GoToSignIn"> Sign In </a>

	<c:if test="${sessionScope.role != null}">
		<a href="welcome?command=LogOut"> Log Out </a>
	</c:if>

	<br>

	Role ${sessionScope.role}

	<h2>Minsk Motor depot</h2>
	<p>Our contacts +375291111111</p>
	<p> <a href="presentation"> Our cars </a> </p>
	<input type="image" src="image/peugeot_boxer_2020.png" width="800" /><br>
	Peugeot Boxer<br>
	<input type="image" src="image/ManTGX.png" width="800" /><br>
	Man TGX
<p></p>
</body>
</html>
