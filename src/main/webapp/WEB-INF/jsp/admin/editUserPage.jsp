<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>
<%@ page import="by.epam.jwd.entity.Status" %>
<%@ page import="by.epam.jwd.entity.Role" %>
<html>
<head>
    <title>Edit user page</title>
</head>
<body>
<br><br>

<c:if test="${param.flag == 'create'}">
    <fmt:message bundle="${loc}" key="table.user.createNewUser"/>
</c:if>

<c:if test="${param.flag == 'update'}">
    <fmt:message bundle="${loc}" key="table.edit"/>
    <c:out value="${user.name}"/> <c:out value="${user.surname}"/>
</c:if>

<b style="color: red"><c:out value="${param.message}"/></b>

<form action="admin" method="get">
    <input type="hidden" name="edit_id" value="${user.id}">
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
        </tr>
        <tr>
            <td><input type="text" name="name" value="${user.name}" placeholder="Ivan" size="4" maxlength="13"/></td>
            <td><input type="text" name="surname" value="${user.surname}" placeholder="Ivanov" size="5" maxlength="13"/>
            </td>
            <td><input type="text" name="login" value="${user.login}" placeholder="Login" size="3" maxlength="13"/></td>
            <td><input type="text" name="password" value="${user.password}" placeholder="At least 6 characters" size="6"
                       maxlength="13"/></td>
            <td><input type="text" name="phoneNumber" value="${user.phoneNumber}" placeholder="+375291234567" size="8"
                       maxlength="13"/></td>
            <td><select name="status">
                <c:forEach var="s" items="${Status.values()}">
                    <c:choose>
                        <c:when test="${user.status == s}">
                            <option selected value="${s}"><c:out value="${s}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s}"><c:out value="${s}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
            <td><input type="text" name="eMail" value="${user.eMail}" placeholder="name@email.com" size="6"
                       maxlength="20"/></td>
            <td><textarea name="additionalInfo" rows="4" cols="10">${user.additionalInfo}</textarea></td>
            <td><select name="role">
                <c:forEach var="s" items="${Role.values()}">
                    <c:choose>
                        <c:when test="${user.role == s}">
                            <option selected value="${s}"><c:out value="${s}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s}"><c:out value="${s}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
        </tr>
    </table>
    <br>
    <c:if test="${param.flag == 'create'}">
        <button type="submit" name="command" value="EditUser"><fmt:message bundle="${loc}" key="table.create"/></button>
        <input type="hidden" name="flag" value="create">
    </c:if>
    <c:if test="${param.flag == 'update'}">
        <button type="submit" name="command" value="EditUser"><fmt:message bundle="${loc}" key="table.update"/></button>
        <input type="hidden" name="flag" value="update">
    </c:if>
</form>
</body>
</html>

