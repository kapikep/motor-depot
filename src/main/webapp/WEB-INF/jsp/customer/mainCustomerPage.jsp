<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/_localization.jsp"%>
<html>
<head>
    <title>Customer page</title>
</head>


<body>
<link href="css/table.css" rel="stylesheet" >
<div style="background: rgba(2,186,171,0.37); height: 30px; padding: 5px; border-radius: 10px;">
    <div style="float: right; padding: 5px; text-align: right;">
        <c:out value="${welcomeMessage}"/> <b>${sessionScope.userFullName}</b>
        <a href="welcome?command=LogOut"> <fmt:message bundle="${loc}" key="welcome.logOut"/></a>
        <br/>
    </div>
    <div style="padding: 5px;">
        <a href="driver"> <fmt:message bundle="${loc}" key="admin.main"/></a>
    </div>
</div>

<fmt:message bundle="${loc}" key="admin.orders"/>
<jsp:include page="/WEB-INF/jsp/_pagination.jsp"/>

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
        <th><fmt:message bundle="${loc}" key="table.order.totalAmount"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.paymentStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.driverFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
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
            <td><c:out value="${order.totalAmount}"/></td>
            <td><c:out value="${order.paymentStatus}"/></td>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.driverName}"/> <c:out value="${order.driverSurname}"/></td>
            <td>
                <a href="admin?command=GoToCarsPage&license_plate=${order.carLicensePlate}">
                    <c:out value="${order.carLicensePlate}"/>
                </a>
            </td>
            <td>
                <a href="admin?command=GoToEditOrder&edit_id=${order.id}">
                    <fmt:message bundle="${loc}" key="table.edit"/></a>
            </td>
        </tr>
    </c:forEach>

    <c:forEach var="i" begin="1" end="12">
        <td></td>
    </c:forEach>

    <td>
        <a href="?command=GoToEditOrder">
            <fmt:message bundle="${loc}" key="table.add"/>
        </a>
    </td>
</table>
<fmt:message bundle="${loc}" key="table.page"/> <c:out value="${page}"/>
<fmt:message bundle="${loc}" key="table.of"/> <c:out value="${pageCount}"/>
</body>
</html>