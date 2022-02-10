<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>
<%@ page import="by.epam.jwd.entity.Status" %>

<html>
<head>
    <title>Edit order Page</title>
</head>
<body>
<br>
<br>
<br>

<fmt:message bundle="${loc}" key="table.order.orderNumber"/><c:out value="${param.edit_id}"/>
<b style="color: red"><c:out value="${param.message}"/></b>

<form action="driver" method="get">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.order.criteria"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.requestDate"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.departPlace"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.arrivalPlace"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.startDate"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.endDate"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.distance"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.contactDetails"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.clientNumber"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.status"/> <fmt:message bundle="${loc}" key="car.car"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.adminFullName"/></th>
        </tr>
        <tr>
            <td><textarea name="criteria" rows="4" cols="20">${order.criteria}</textarea></td>
            <td><fmt:formatDate value="${order.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><textarea name="departPlace" rows="4" cols="8">${order.departPlace}</textarea></td>
            <td><textarea name="arrivalPlace" rows="4" cols="8">${order.arrivalPlace}</textarea></td>
            <td><input type="datetime-local" name="startDate"
                       value="<fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"></td>
            <td><input type="datetime-local" name="endDate"
                       value="<fmt:formatDate value="${order.endDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"></td>
            <td><input type="text" name="distance" value="${order.distance}" size="3" maxlength="6" minlength="1"/></td>
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
            <td><c:out value="${order.contactDetails}"/></td>
            <td><c:out value="${order.clientPhone}"/></td>
            <td><textarea name="carStatus" rows="4" cols="8">${car.status}</textarea></td>
            <td><c:out value="${order.adminName}"/> <c:out value="${order.adminSurname}"/></td>
        </tr>
    </table>
    <br>
    <input type="hidden" name="edit_id" value="${param.edit_id}">
    <button type="submit" name="command" value="UpdateOrderByDriver"><fmt:message bundle="${loc}" key="table.update"/></button>
</form>
</body>
</html>
