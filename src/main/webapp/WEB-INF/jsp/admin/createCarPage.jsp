<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create cars Page</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<br>

<h3 style="padding: 5px;">Create car</h3>

<form action="admin" method="get">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Licence Plate</th>
            <th>Color</th>
            <th>Odometr</th>
            <th>Status</th>
            <th>Model</th>
        </tr>

        <tr>
            <td><input type="text" name="licencePlate" value="${car.licencePlate}" size="8" maxlength="9"/></td>
            <td><input type="text" name="color" value="${car.color}" size="3" maxlength="9"/></td>
            <td><input type="text" name="odometr" value="${car.odometr}" size="5" maxlength="9" pattern="^[ 0-9]+$"/></td>
            <td><input type="text" name="status" value="${car.status}" size="4" maxlength="9"></td>
            <td>
                <select name="carModel">
                    <c:forEach var="carModel" items="${carModels}">
                        <c:choose>
                        <c:when test="${carModel.id == car.carModelId}">
                            <option selected value="${carModel.id}"><c:out value="${carModel}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${carModel.id}"><c:out value="${carModel}"/></option>
                        </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <c:if test="${create != null}">
        <button type="submit" name="command" value="CreateCar">Create car</button>
    </c:if>
    <c:if test="${edit != null}">
        <button type="submit" name="command" value="UpdateCar">Update car</button>
        <button type="submit" name="command" value="DeleteCar">Delete car</button>
    </c:if>

</form>
<br>

<h3 style="padding: 5px;">Create car model</h3>
<form action="admin" method="get">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Model Name</th>
            <th>Type</th>
            <th>Load Capacity</th>
            <th>Passenger Capacity</th>
            <th>Wheel Drive Type</th>
        </tr>
        <tr>
            <td><input type="text" name="modelName" value="${car.modelName}" size="7" maxlength="13"/></td>
            <td><input type="text" name="type" value="${car.type}" size="5" maxlength="9"/></td>
            <td><input type="text" name="loadCapacity" value="${car.loadCapacity}" size="5" maxlength="9" pattern="^[ 0-9]+$"/></td>
            <td><input type="number" name="passengerCapacity" value="${car.passengerCapacity}" size="4" maxlength="4"></td>
            <td><input type="text" name="wheelDriveType" value="${car.wheelDriveType}" ize="4" maxlength="9"></td>
        </tr>
    </table>
    <br>
        <button type="submit" name="command" value="CreateCarModel">Create car model</button>
    <c:if test="${edit != null}">
        <button type="submit" name="command" value="UpdateCarModel">Update car model</button>
        <button type="submit" name="command" value="DeleteCarModel">Delete car model</button>
    </c:if>
    <br>
</form>
</body>
</html>
