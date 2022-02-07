<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp"%>
<html>
<head>
    <title>Users Page</title>
</head>
<body>
<br>
<form action="" method="get">
    <fmt:message bundle="${loc}" key="table.user.users"/>
    <input name="user name" placeholder="Ivan Ivanov" type="search" maxlength="9" minlength="9">
    <button type="submit" name="command" value=${param.command}>
        <fmt:message bundle="${loc}" key="table.user.searchByName"/>
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
        <th><fmt:message bundle="${loc}" key="table.user.role"/></th>
        <th><fmt:message bundle="${loc}" key="table.edit"/></th>
    </tr>

    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.phoneNumber}"/></td>
            <td><c:out value="${user.status}"/></td>
            <td><c:out value="${user.eMail}"/></td>
            <td><c:out value="${user.additionalInfo}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <a href="admin?command=GoToEditUser&edit_id=${user.id}&flag=update">
                    <fmt:message bundle="${loc}" key="table.edit"/></a>
            </td>
        </tr>
    </c:forEach>

    <c:forEach var="i" begin="1" end="9">
        <td></td>
    </c:forEach>

    <td>
        <a href="?command=GoToEditUser&flag=create">
            <fmt:message bundle="${loc}" key="table.add"/>
        </a>
    </td>
</table>
<fmt:message bundle="${loc}" key="table.page"/> <c:out value="${page}"/>
<fmt:message bundle="${loc}" key="table.of"/> <c:out value="${pageCount}"/>
</body>
</html>
