<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>
<html>
<head>
    <title>Orders Page</title>
</head>
<body>
<fmt:message bundle="${loc}" key="admin.orders"/>
<jsp:include page="/WEB-INF/jsp/_pagination.jsp"/>

<c:out value="${param.message}"/>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th><fmt:message bundle="${loc}" key="table.order.orderNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.criteria"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.requestDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.startDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.clientFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.driverFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
        <th><fmt:message bundle="${loc}" key="table.view"/></th>
    </tr>
    <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.criteria}"/></td>
            <td><fmt:formatDate value="${order.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.clientSurname}"/> <c:out value="${order.clientName}"/></td>
            <td><c:out value="${order.driverName}"/> <c:out value="${order.driverSurname}"/></td>
            <td><c:out value="${order.carLicensePlate}"/></td>
            <td>
                <a href="admin?command=GoToEditCar&edit_id=${order.id}">
                    <fmt:message bundle="${loc}" key="table.view"/></a>
            </td>
        </tr>
    </c:forEach>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>
        <a href="?command=GoToEditCar" style="padding: 10px;">
            <fmt:message bundle="${loc}" key="table.car.add"/>
        </a>
    </td>
</table>
<fmt:message bundle="${loc}" key="table.page"/> <c:out value="${page}"/>
<fmt:message bundle="${loc}" key="table.of"/> <c:out value="${pageCount}"/>

</body>
</html>

