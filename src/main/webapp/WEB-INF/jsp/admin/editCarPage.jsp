<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp"%>

<html>
<head>
    <title>Edit cars Page</title>
    <fmt:message bundle="${loc}" key="table.create" var="Create"/>
    <fmt:message bundle="${loc}" key="table.update" var="Update"/>
    <fmt:message bundle="${loc}" key="table.delete" var="Delete"/>
    <fmt:message bundle="${loc}" key="car.car" var="carN"/>
    <fmt:message bundle="${loc}" key="car.carModel" var="carModelN"/>
</head>
<body>
<br>

<h3 style="padding: 5px;">${Create} ${carN}</h3>

<form action="admin" method="get">
    <input type="hidden" name="id" value="${car.id}">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.color"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.odometr"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.status"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.model"/></th>
        </tr>
        <tr>
            <td><input type="text" name="licencePlate" value="${car.licencePlate}" placeholder="7777 KC-7" size="8" maxlength="9" minlength="9"/></td>
            <td><input type="text" name="color" value="${car.color}" placeholder="blue" size="3" maxlength="9"/></td>
            <td><input type="text" name="odometr" value="${car.odometr}" placeholder="7777" size="5" maxlength="9" pattern="^[ 0-9]+$"/></td>
            <td><input type="text" name="status" placeholder="active" value="${car.status}" size="4" maxlength="9"></td>
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
        <button type="submit" name="command" value="CreateCar">${Create}</button>
    </c:if>
    <c:if test="${edit != null}">
        <button type="submit" name="command" value="UpdateCar">${Update}</button>
        <button type="submit" name="command" value="DeleteCar">${Delete}</button>
    </c:if>

</form>
<br>

<h3 style="padding: 5px;">${Create} ${carModelN}</h3>
<form action="admin" method="get">
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.car.model"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.type"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.loadCap"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.passengerCap"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.weelDr"/></th>
        </tr>
        <tr>
            <td><input type="text" name="modelName" value="${car.modelName}" placeholder="Nissan Murano" size="7" maxlength="13"/></td>
            <td><input type="text" name="type" value="${car.type}" placeholder="Van" size="5" maxlength="9"/></td>
            <td><input type="text" name="loadCapacity" value="${car.loadCapacity}" placeholder="1000" size="5" maxlength="9" pattern="^[ 0-9]+$"/></td>
            <td><input type="number" name="passengerCapacity" value="${car.passengerCapacity}" placeholder="8" size="4" maxlength="4"></td>
            <td><input type="text" name="wheelDriveType" value="${car.wheelDriveType}" ize="4"placeholder="4x2"  maxlength="9"></td>
        </tr>
    </table>
    <br>
        <button type="submit" name="command" value="CreateCarModel">${Create}</button>
    <c:if test="${edit != null}">
        <button type="submit" name="command" value="UpdateCarModel">${Update}</button>
        <button type="submit" name="command" value="DeleteCarModel">${Delete}</button>
    </c:if>
    <br>
</form>
</body>
</html>
