<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
</head>
<body>

<form method="post">
    <select name="rowLimit">
        <c:forEach var="i" begin="10" end="90" step="10">
            <c:choose>
                <c:when test="${i==rowLimit}">
                    <option selected>${i}</option>
                </c:when>
                <c:otherwise>
                    <option>${i}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <button type="submit" name="command" value=${param.command}><fmt:message bundle="${loc}" key="welcome.set"/></button>
</form>
<c:if test="${page != 1}">
    <a href="admin?command=${param.command}&page=${page - 1}"><fmt:message bundle="${loc}" key="pagination.prev"/></a>
</c:if>
<c:forEach var="i" items="${numPages}">
    <c:choose>
        <c:when test="${i==page}">
            <b><a href="admin?command=${param.command}&page=${i}"><c:out value="${i}"/></a></b>
        </c:when>
        <c:otherwise>
            <a href="admin?command=${param.command}&page=${i}"><c:out value="${i}"/></a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${page != pageCount}">
    <a href="admin?command=${param.command}&page=${page + 1}"><fmt:message bundle="${loc}" key="pagination.next"/></a>
</c:if>
</body>
</html>
