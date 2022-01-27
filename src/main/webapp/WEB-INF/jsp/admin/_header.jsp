<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>

<link href="css/table.css" rel="stylesheet" >
<div style="background: rgba(2,186,171,0.37); height: 30px; padding: 5px; border-radius: 10px;">
    <div style="float: right; padding: 5px; text-align: right;">
        <c:out value="${welcomeMessage}"/> <b>${sessionScope.name}</b>
        <a href="welcome?command=LogOut"> <fmt:message bundle="${loc}" key="welcome.logOut"/></a>
        <br/>
    </div>
    <div style="padding: 5px;">
        <a href="admin"> <fmt:message bundle="${loc}" key="admin.main"/></a>
        |
        <a href="?command=GoToOrdersPage"> <fmt:message bundle="${loc}" key="admin.orders"/></a>
        |
        <a href="?command=GoToEmployeesPage"> <fmt:message bundle="${loc}" key="admin.employees"/></a>
        |
        <a href="?command=GoToCustomersPage"> <fmt:message bundle="${loc}" key="admin.customers"/></a>
        |
        <a href="?command=GoToCarsPage"> <fmt:message bundle="${loc}" key="admin.cars"/></a>
    </div>
</div>
