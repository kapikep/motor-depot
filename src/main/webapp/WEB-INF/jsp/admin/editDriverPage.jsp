<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>
<%@ page import="by.epam.jwd.entity.Status" %>

<html>
<head>
    <title>Edit driver page</title>
</head>
<body>
<br><br>
<c:out value="${param.message}"/>

<c:if test="${create != null}">
    <fmt:message bundle="${loc}" key="table.driver.createNewDriver"/>
</c:if>

<c:if test="${edit != null}">
    <fmt:message bundle="${loc}" key="table.edit"/> <c:out value="${param.edit_id}"/>
</c:if>

<form action="admin" method="get">
    <input type="hidden" name="id" value="${driver.id}">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.user.name"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.surname"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.login"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.password"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.phone_number"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.status"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.e-mail"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.additionalInfo"/></th>
            <th><fmt:message bundle="${loc}" key="table.driver.category"/></th>
            <th><fmt:message bundle="${loc}" key="table.driver.drivingExperience"/></th>
            <th><fmt:message bundle="${loc}" key="table.driver.dateOfEmployment"/></th>
            <th><fmt:message bundle="${loc}" key="table.driver.dateOfDismissal"/></th>
        </tr>
        <tr>
            <td><input type="text" name="name" value="${driver.name}" placeholder="Ivan" size="3" maxlength="13"/></td>
            <td><input type="text" name="surname" value="${driver.surname}" placeholder="Ivanov" size="3" maxlength="13"/></td>
            <td><input type="text" name="login" value="${driver.login}" placeholder="Login" size="3" maxlength="13"/></td>
            <td><input type="text" name="password" value="${driver.password}" placeholder="At least 6 characters" size="6" maxlength="13"/></td>
            <td><input type="text" name="phoneNumber" value="${driver.phoneNumber}" placeholder="+375291234567" size="6" maxlength="13"/></td>
            <td><select name="status">
                <c:forEach var="s" items="${Status.values()}">
                    <c:choose>
                        <c:when test="${driver.status == s}">
                            <option selected value="${s}"><c:out value="${s}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s}"><c:out value="${s}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
            <td><input type="text" name="eMail" value="${driver.eMail}" placeholder="name@email.com" size="6" maxlength="20"/></td>
            <td><textarea name="additionalInfo" rows="4" cols="10">${driver.additionalInfo}</textarea></td>
            <td><input type="text" name="category" value="${driver.category}" placeholder="A, B, C, D" size="1" maxlength="20"/></td>
            <td><input type="text" name="drivingExperience" value="${driver.drivingExperience}" placeholder="1" size="1" maxlength="20"/></td>
            <td><input type="datetime-local" name="dateOfEmployment"
                       value="<fmt:formatDate value="${driver.dateOfEmployment}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"></td>
            <td><input type="datetime-local" name="dateOfDismissal"
                       value="<fmt:formatDate value="${driver.dateOfDismissal}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"></td>
        </tr>
    </table>
    <br>
    <c:if test="${create != null}">
        <button type="submit" name="command" value="EditDriver"><fmt:message bundle="${loc}" key="table.create"/></button>
        <input type="hidden" name="create" value="true">
    </c:if>
    <c:if test="${edit != null}">
        <button type="submit" name="command" value="EditDriver"><fmt:message bundle="${loc}" key="table.update"/></button>
        <input type="hidden" name="update" value="true">
    </c:if>
</form>
</body>
</html>

