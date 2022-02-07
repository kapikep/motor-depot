<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>
<html>
<head>
    <title>Drivers page</title>
</head>
<body>
<br>
<form action="" method="get">
    <fmt:message bundle="${loc}" key="table.driver.drivers"/>&nbsp
    <input name="driver name" placeholder="Ivan Ivanov" type="search" maxlength="15" minlength="3">
    <button type="submit" name="command" value=${param.command}>
        <fmt:message bundle="${loc}" key="table.driver.searchByName"/>
    </button>
</form>
<jsp:include page="/WEB-INF/jsp/_pagination.jsp"/>

<b style="color: red"><c:out value="${param.message}"/></b>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th><fmt:message bundle="${loc}" key="table.user.name"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.surname"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.login"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.password"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.phoneNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.status"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.e-mail"/></th>
        <th><fmt:message bundle="${loc}" key="table.user.additionalInfo"/></th>
        <th><fmt:message bundle="${loc}" key="table.driver.category"/></th>
        <th><fmt:message bundle="${loc}" key="table.driver.drivingExperience"/></th>
        <th><fmt:message bundle="${loc}" key="table.driver.dateOfEmployment"/></th>
        <th><fmt:message bundle="${loc}" key="table.driver.dateOfDismissal"/></th>
        <th><fmt:message bundle="${loc}" key="table.edit"/></th>
    </tr>

    <c:forEach items="${requestScope.drivers}" var="driver">
        <tr>
            <td><c:out value="${driver.name}"/></td>
            <td><c:out value="${driver.surname}"/></td>
            <td><c:out value="${driver.login}"/></td>
            <td><c:out value="${driver.password}"/></td>
            <td><c:out value="${driver.phoneNumber}"/></td>
            <td><c:out value="${driver.status}"/></td>
            <td><c:out value="${driver.eMail}"/></td>
            <td><c:out value="${driver.additionalInfo}"/></td>
            <td><c:out value="${driver.category}"/></td>
            <td><c:out value="${driver.drivingExperience}"/></td>
            <td><fmt:formatDate value="${driver.dateOfEmployment}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${driver.dateOfDismissal}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <a href="admin?command=GoToEditDriver&edit_id=${driver.id}&flag=update">
                    <fmt:message bundle="${loc}" key="table.edit"/></a>
            </td>
        </tr>
    </c:forEach>

    <c:forEach var="i" begin="1" end="12">
        <td></td>
    </c:forEach>

    <td>
        <a href="?command=GoToEditDriver&flag=create">
            <fmt:message bundle="${loc}" key="table.add"/>
        </a>
    </td>
</table>
<fmt:message bundle="${loc}" key="table.page"/> <c:out value="${page}"/>
<fmt:message bundle="${loc}" key="table.of"/> <c:out value="${pageCount}"/>
</body>
</html>
