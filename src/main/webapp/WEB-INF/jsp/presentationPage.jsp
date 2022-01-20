<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
Our Cars
<ul>
    <c:forEach var="cookies" items="${cookie}">
        <li>
            <br>
            <c:out value="${cookies.key}" />:
            <br>
            Object=
            <c:out value="${cookies.value}" />,
            <br>
            value=
            <c:out value="${cookies.value.value}" />
        </li>
    </c:forEach>
</ul>
</body>
</html>