<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <jsp:include page="/WEB-INF/jsp/_pagination.jsp"/>
    <br>
    <tr>
        <th>Company</th>
        <th>Q1</th>
        <th>Q2</th>
        <th>Q3</th>
        <th>Q4</th>
    </tr>
    <tr>
        <td>Microsoft</td>
        <td>20.3</td>
        <td>30.5</td>
        <td>23.5</td>
        <td>40.3</td>
    </tr>
    <tr>
        <td>Google</td>
        <td>50.2</td>
        <td>40.63</td>
        <td>45.23</td>
        <td>39.3</td>
    </tr>
    <tr>
        <td>Apple</td>
        <td>25.4</td>
        <td>30.2</td>
        <td>33.3</td>
        <td>36.7</td>
    </tr>
    <tr>
        <td>IBM</td>
        <td>20.4</td>
        <td>15.6</td>
        <td>22.3</td>
        <td>29.3</td>
    </tr>
</table>
cust page
</body>
</html>
