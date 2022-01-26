<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>

<form action="admin" method="get">
    <input type="datetime-local" name="date">
    <button type="submit" name="command" value=${param.command}>Set</button>
</form>

Orders page
</body>
</html>
