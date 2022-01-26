<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<c:if test="${page != 1}">
    <a href="admin?command=${param.command}&page=${page - 1}">Prev</a>
</c:if>
<c:forEach var="i" items="${numPages}">
    <a href="admin?command=${param.command}&page=${i}"><c:out value="${i}"/></a>
</c:forEach>
<c:if test="${page != pageCount}">
    <a href="admin?command=${param.command}&page=${page + 1}">Next</a>
</c:if>
<form method="get">
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
    <button type="submit" name="command" value=${param.command}>Set</button>
</form>
${param.command}
</body>
</html>
