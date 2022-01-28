<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="by.epam.jwd.entity.Role" %>
<%@ page import="by.epam.jwd.dao.implementation.MariaDbCarDAO" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
Our Cars
<br>
<%-- value="${Role.ADMIN}"--%>
<%--${MariaDBCarDAO.CREATE_CAR}--%>

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
${sessionScope.size()}"
<br>

</body>
</html>