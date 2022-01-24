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
    <input type="hidden" name="command" value="CreateCar">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Licence Plate</th>
            <th>Color</th>
            <th>Odometr</th>
            <th>Status</th>
            <th>Model</th>
        </tr>

        <tr>
            <td><input type="text" name="licencePlate" value="7777 KC-7" size="8" maxlength="9"/></td>
            <td><input type="text" name="color" value="blue" size="3" maxlength="9"/></td>
            <td><input type="text" name="odometr" value="111" size="5" maxlength="9" pattern="^[ 0-9]+$"/></td>
            <td><input type="text" name="status" value="active" size="4" maxlength="9"></td>
            <td>
                <select name="carModel">
                    <c:forEach var="carModel" items="${carModels}">
                        <option value="${carModel.id}"><c:out value="${carModel}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Create car"/>
</form>
<br>
<h3 style="padding: 5px;">Create car model</h3>
<form action="admin" method="get">
    <input type="hidden" name="command" value="CreateCarModel">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>Model Name</th>
            <th>Type</th>
            <th>Load Capacity</th>
            <th>Passenger Capacity</th>
            <th>Wheel Drive Type</th>
        </tr>
        <tr>
            <td><input type="text" name="modelName" value="Nissan Murano" size="8" maxlength="9"/></td>
            <td><input type="text" name="type" value="Car" size="5" maxlength="9"/></td>
            <td><input type="text" name="loadCapacity" value="1111" size="5" maxlength="9" pattern="^[ 0-9]+$"/></td>
            <td><input type="number" name="passengerCapacity" value="5" size="4" maxlength="4"></td>
            <td><input type="text" name="wheelDriveType" value="2x4" size="4" maxlength="9"></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Create car model"/>
    <br>
</form>
</body>
</html>
