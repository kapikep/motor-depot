<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="welcome.message" var="welcomeMessage"/>
    <fmt:message bundle="${loc}" key="locale.locbutton.name.ru"
                 var="ru_button"/>
    <fmt:message bundle="${loc}" key="locale.locbutton.name.en"
                 var="en_button"/>
</head>
<body>
<form action="" method="get" style="position: absolute; top: 65px; right: 20px;">
    <input type="hidden" name="command" value="ChangeLocalization">
    <input type="hidden" name="prev_command" value="${param.command}">
    <input type="hidden" name="edit_id" value="${param.edit_id}">
    <input type="hidden" name="flag" value="${param.flag}">
            <button type="submit" name="locale" value="ru">${ru_button}</button>
            <button type="submit" name="locale" value="en">${en_button}</button>
</form>
</body>
</html>
