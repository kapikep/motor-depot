<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create cars Page</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<br>
<form action="admin" method="get">
    <input type="hidden" name="command" value="CreateCar">
    LicencePlate: <input type="text" name="licencePlate" value=""/> <br>
    Color: <input type="text" name="color" value=""/> <br>
    Odometr: <input type="text" name="odometr" value=""/> <br>
    Status: <input type="text" name="status" value=""/> <br>
    Select car model:
    <select name="carModel">
        <c:forEach var="carModel" items="${carModels}">
            <option value="${carModel.id}"><c:out value="${carModel}"/></option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Create car"/>
</form>
<br>
<br>
<form action="admin" method="get">
    <input type="hidden" name="command" value="CreateCarModel">

    ModelName: <input type="text" name="modelName" value=""/> <br>
    Type: <input type="text" name="type" value=""/> <br>
    LoadCapacity: <input type="text" name="loadCapacity" value=""/> <br>
    Passenger Capacity: <input type="text" name="passengerCapacity" value=""/> <br>
    Wheel Drive Type: <input type="text" name="wheelDriveType" value=""/> <br>
    <input type="submit" value="Create car model"/>
    <br>
</form>
</body>
</html>
