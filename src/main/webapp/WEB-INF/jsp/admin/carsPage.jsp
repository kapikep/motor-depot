<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Manage cars</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<br>
<form action="" method="get">
    <input name="s" placeholder="Искать здесь..." type="search">
    <button type="submit">Поиск</button>
</form>
<h4>Car List
    <jsp:include page="/WEB-INF/jsp/_pagination.jsp"/>
</h4>
<c:out value="${param.message}"/>
<%-- TODO delete id--%>
<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Id</th>
        <th>Model</th>
        <th>Licence Plate</th>
        <th>Color</th>
        <th>Type</th>
        <th>Load Capacity</th>
        <th>Passenger Capacity</th>
        <th>Wheel Drive Type</th>
        <th>Odometr</th>
        <th>Status</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${requestScope.cars}" var="car" >
        <tr>
            <td><c:out value="${car.id}"/></td>
            <td><c:out value="${car.modelName}"/></td>
            <td><c:out value="${car.licencePlate}"/></td>
            <td><c:out value="${car.color}"/></td>
            <td><c:out value="${car.type}"/></td>
            <td><c:out value="${car.loadCapacity}"/></td>
            <td><c:out value="${car.passengerCapacity}"/></td>
            <td><c:out value="${car.wheelDriveType}"/></td>
            <td><c:out value="${car.odometr}"/> km </td>
            <td><c:out value="${car.status}"/></td>
            <td>
                <a href="admin?command=GoToEditCar&edit_id=${car.id}">Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>

Page <c:out value="${page}" /> of ${pageCount} pages

<a href="?command=GoToEditCar" style="float: right; padding: 10px; text-align: right;">Add Car</a>
</body>
</html>
