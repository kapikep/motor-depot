<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>
<%@ include file="_header.jsp"%>
<html>
<head>
    <title>My orders</title>
</head>

<body>
<br>
<form action="" method="get">
    <fmt:message bundle="${loc}" key="table.orders"/>
    <input name="findId" placeholder="Order №" type="search" maxlength="9" minlength="1">
    <button type="submit" name="command" value=${param.command}>
        <fmt:message bundle="${loc}" key="table.search"/>
    </button>
</form>

<jsp:include page="/WEB-INF/jsp/driver/_pagination.jsp"/>

<b style="color: red"><c:out value="${param.message}"/></b>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th><fmt:message bundle="${loc}" key="table.car.model"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.color"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.type"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.loadCap"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.passengerCap"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.weelDr"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.odometr"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.status"/></th>
        <th><fmt:message bundle="${loc}" key="table.edit"/></th>
    </tr>
    <c:forEach items="${requestScope.cars}" var="user">
    <tr>
        <td><c:out value="${user.modelName}"/></td>
        <td><c:out value="${user.licencePlate}"/></td>
        <td><c:out value="${user.color}"/></td>
        <td><c:out value="${user.type}"/></td>
        <td><c:out value="${user.loadCapacity}"/></td>
        <td><c:out value="${user.passengerCapacity}"/></td>
        <td><c:out value="${user.wheelDriveType}"/></td>
        <td><c:out value="${user.odometr}"/> km</td>
        <td><c:out value="${user.status}"/></td>
        <td>
            <a href="driver?command=GoToEditCar&edit_id=${user.id}">
                <fmt:message bundle="${loc}" key="table.edit"/></a>
        </td>
    </tr>
    </c:forEach>
</body>
</html>
