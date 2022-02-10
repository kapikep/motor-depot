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

<c:out value="${param.message}"/>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th><fmt:message bundle="${loc}" key="table.order.orderNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.criteria"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.requestDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.departPlace"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.arrivalPlace"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.startDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.endDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.contactDetails"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.clientNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.driverFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.adminFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.edit"/></th>
    </tr>
    <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.criteria}"/></td>
            <td><fmt:formatDate value="${order.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><c:out value="${order.departPlace}"/></td>
            <td><c:out value="${order.arrivalPlace}"/></td>
            <td><fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${order.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.contactDetails}"/> </td>
            <td><c:out value="${order.clientPhone}"/> </td>
            <td><c:out value="${order.driverName}"/> <c:out value="${order.driverSurname}"/></td>
            <td><c:out value="${order.carLicensePlate}"/></td>
            <td><c:out value="${order.adminName}"/> <c:out value="${order.adminSurname}"/></td>
            <td>
                <a href="driver?command=GoToDriverEditOrder&edit_id=${order.id}">
                    <fmt:message bundle="${loc}" key="table.edit"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
