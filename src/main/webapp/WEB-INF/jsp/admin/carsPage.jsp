<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Manage cars</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>

<h3 style="padding: 5px;">Car List</h3>

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
        <th>Delete</th>
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
                <a href="editProduct?code=${product.code}">Edit</a>
            </td>
            <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="createProduct" style="padding: 5px;">Add Car</a>

</body>
</html>
