<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<link href="css/welcCreateOrder.css" rel="stylesheet" >
<form class="form" action="welcome" method="post">
    <input type="hidden" name="command" value="CreateOrder">
    <h2><fmt:message bundle="${loc}" key="welcome.createOrder"/></h2>
    <p type="Full name:"><input placeholder="Write your name here.." name="contactDetails"></input></p>
    <p type="Phone"><input placeholder="Let us know how to contact you back.." name="clientPhone"></input></p>
    <p type="Message:"><input placeholder="What kind of car do you need" name="criteria"></input></p>
    <button><fmt:message bundle="${loc}" key="table.send"/></button>
    <div>
        <span class="fa fa-phone"></span>+375291111111
        <span class="fa fa-envelope-o"></span> motor_dep@mail.com
    </div>
</form>
</body>
</html>