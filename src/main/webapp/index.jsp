<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>
<!DOCTYPE html>
<html>
<body>
<form method="get">
</form>

<div style="float: right; padding: 15px; text-align: right;">
	<a href="welcome?command=GoToSignIn"> <fmt:message bundle="${loc}" key="welcome.signIn"/> </a>
	<c:if test="${sessionScope.role != null}">
		<a href="welcome?command=LogOut"> <fmt:message bundle="${loc}" key="welcome.logOut"/> </a>
	</c:if>
</div>

	<br>
	Role ${sessionScope.role}

	<h2><fmt:message bundle="${loc}" key="welcome.deport"/></h2>
	<p><fmt:message bundle="${loc}" key="welcome.ourCont"/> +375291111111</p>
	<p> <a href="presentation"> <fmt:message bundle="${loc}" key="welcome.createOrder"/> </a> </p>
	<input type="image" src="image/peugeot_boxer_2020.png" width="800" /><br>
	Peugeot Boxer<br>
	<input type="image" src="image/ManTGX.png" width="800" /><br>
	Man TGX
<p></p>
</body>
</html>
