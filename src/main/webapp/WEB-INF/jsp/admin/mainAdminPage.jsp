<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>

<html>
<head>
<meta charset="UTF-8">
<title>Main admin page</title>
</head>
<body>
<br>
<br>
<fmt:message bundle="${loc}" key="table.orders"/>

<b style="color: red"><c:out value="${param.message}"/></b>

<table border="1" cellpadding="5" cellspacing="1">
    <tr>
        <th><fmt:message bundle="${loc}" key="table.order.orderNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.criteria"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.requestDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.startDate"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.contactDetails"/></th>
        <th><fmt:message bundle="${loc}" key="table.order.clientNumber"/></th>
        <th><fmt:message bundle="${loc}" key="table.view"/></th>
    </tr>
    <c:forEach items="${requestScope.orders}" var="order">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.criteria}"/></td>
            <td><fmt:formatDate value="${order.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><c:out value="${order.orderStatus}"/></td>
            <td><c:out value="${order.contactDetails}"/> </td>
            <td><c:out value="${order.clientPhone}"/> </td>
            <td>
                <a href="admin?command=GoToEditOrder&editId=${order.id}&flag=update">
                    <fmt:message bundle="${loc}" key="table.edit"/></a>
            </td>
        </tr>
    </c:forEach>
    <c:forEach var="i" begin="1" end="7">
        <td></td>
    </c:forEach>

    <td>
        <a href="?command=GoToEditOrder&flag=create" style="padding: 10px;">
            <fmt:message bundle="${loc}" key="table.add"/>
        </a>
    </td>
</table>

</body>
</html>