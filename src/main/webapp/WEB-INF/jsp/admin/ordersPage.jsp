<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="admin" method="get">
    <input type="datetime-local" name="date">
    <button type="submit" name="command" value=${param.command}>Set</button>
</form>

Orders page
</body>
</html>
