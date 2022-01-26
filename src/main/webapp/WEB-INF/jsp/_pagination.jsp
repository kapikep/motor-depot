<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${param.page != 1}">
        <a href="admin?command=GoToCarsPage&page=${param.page - 1}">Prev</a>
    </c:when>
    <c:otherwise>
        <a href="admin?command=GoToCarsPage&page=${1}">Prev</a>
    </c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="${pageCount}">
    <a href="admin?command=GoToCarsPage&page=${i}"><c:out value="${i}"/></a>
</c:forEach>

<c:choose>
    <c:when test="${param.page != pageCount}">
        <a href="admin?command=GoToCarsPage&page=${param.page + 1}">Prev</a>
    </c:when>
    <c:otherwise>
        <a href="admin?command=GoToCarsPage&page=${pageCount}">Prev</a>
    </c:otherwise>
</c:choose>
<br>


<c:forEach var="i" begin="${param.page}" end="${5}">
    <a href="admin?command=GoToCarsPage&page=${i}"><c:out value="${i}"/></a>
</c:forEach>

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
</body>
</html>
