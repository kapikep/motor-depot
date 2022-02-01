<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>


<link href="css/table.css" rel="stylesheet" >
<div style="background: rgba(2,186,171,0.37); height: 30px; padding: 5px; border-radius: 10px;">
    <div style="float: right; padding: 5px; text-align: right;">
        <c:out value="${welcomeMessage}"/> <b>${sessionScope.userFullName}</b>
        <a href="welcome?command=LogOut"> <fmt:message bundle="${loc}" key="welcome.logOut"/></a>
        <br/>
    </div>
    <div style="padding: 5px;">
        <a href="driver"> <fmt:message bundle="${loc}" key="table.main"/></a>
        |
        <a href="?command=GoToDriverOrdersPage"> <fmt:message bundle="${loc}" key="table.orders"/></a>
    </div>
</div>
