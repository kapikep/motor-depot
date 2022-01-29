<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>
<%@ page import="by.epam.jwd.entity.Status" %>

<html>
<head>
    <title>Create order Page</title>
</head>
<body>
<br>
<br>
<br>
<fmt:message bundle="${loc}" key="table.order.orderNumber"/><c:out value="${order.id}"/>
<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th><fmt:message bundle="${loc}" key="table.order.criteria"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.requestDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.departPlace"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.arrivalPlace"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.startDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.endDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.distance"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.totalAmount"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.paymentStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.clientFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.clientNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.driverFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.adminFullName"/></th>
        <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
    </tr>
        <tr>
            <td><textarea name="criteria" rows="5" cols="40">${order.criteria}</textarea></td>
            <td><fmt:formatDate value="${order.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><textarea name="departPlace" rows="5" cols="8">${order.departPlace}</textarea></td>
            <td><textarea name="arrivalPlace" rows="5" cols="8">${order.arrivalPlace}</textarea></td>
            <td><input type="datetime-local" name="date" value="<fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"> </td>
            <td><input type="datetime-local" name="date" value="<fmt:formatDate value="${order.endDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"> </td>
            <td><input type="text" name="distance" value="${order.distance}" size="3" maxlength="30" minlength="5"/></td>
            <td><input type="text" name="departPlace" value="${order.totalAmount}" size="3" maxlength="30" minlength="5"/></td>
            <td><input type="text" name="paymentStatus" value="${order.paymentStatus}" size="4" maxlength="30" minlength="5"/></td>
            <td><select name="status">
                <c:forEach var="s" items="${Status.values()}">
                    <c:choose>
                        <c:when test="${order.orderStatus == s}">
                            <option selected value="${s}"><c:out value="${s}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s}"><c:out value="${s}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
            <td><textarea name="clientFullName" rows="5" cols="8">${order.clientFullName}</textarea></td>
            <td><input type="text" name="clientPhone" value="${order.clientPhone}" size="8" maxlength="30" minlength="5"/></td>
            <td><c:out value="${order.driverName}"/> <c:out value="${order.driverSurname}"/></td>
            <td><c:out value="${order.adminName}"/> <c:out value="${order.adminSurname}"/></td>

            <td>
                <a href="admin?command=GoToCarsPage&license_plate=${order.carLicensePlate}">
                    <c:out value="${order.carLicensePlate}"/>
                </a>
            </td>
        </tr>
</table>
<br>
<c:out value="${car}" />
</body>
</html>
