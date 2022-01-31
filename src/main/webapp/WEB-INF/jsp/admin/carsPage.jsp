<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>

<html>
<head>
    <title>Manage cars</title>
</head>

<body>
<br>
<form action="" method="get">
    <input name="license_plate" placeholder="6566 PE-7" type="search" maxlength="9" minlength="9">
    <button type="submit" name="command" value=${param.command}>
        <fmt:message bundle="${loc}" key="admin.searchByLicensePlate"/>
    </button>
</form>

<fmt:message bundle="${loc}" key="admin.cars"/>
<jsp:include page="/WEB-INF/jsp/_pagination.jsp"/>

<c:out value="${param.message}"/>

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
                <a href="admin?command=GoToEditCar&edit_id=${user.id}">
                    <fmt:message bundle="${loc}" key="table.edit"/></a>
            </td>
        </tr>
    </c:forEach>

    <c:forEach var="i" begin="1" end="9">
        <td></td>
    </c:forEach>

    <td>
        <a href="?command=GoToEditCar" style="padding: 10px;">
            <fmt:message bundle="${loc}" key="table.add"/>
        </a>
    </td>
</table>
<fmt:message bundle="${loc}" key="table.page"/> <c:out value="${page}"/>
<fmt:message bundle="${loc}" key="table.of"/> <c:out value="${pageCount}"/>
</body>
</html>
