<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>
<%@ page import="by.epam.jwd.entity.Status" %>

<html>
<head>
    <title>Edit order Page</title>
</head>
<body>
<br>
<br>
<form action="admin" method="get">
    <c:if test="${param.flag == 'create'}">
        <fmt:message bundle="${loc}" key="table.order.createNewOrder"/>
        <button type="submit" name="command" value="EditOrder"><fmt:message bundle="${loc}" key="table.create"/></button>
    </c:if>

    <c:if test="${param.flag == 'update'}">
        <fmt:message bundle="${loc}" key="table.order.orderNumber"/><c:out value="${param.editId}"/>
        <button type="submit" name="command" value="EditOrder"><fmt:message bundle="${loc}" key="table.update"/></button>
        <input type="hidden" name="editId" value="${order.id}">
    </c:if>
    <input type="hidden" name="flag" value="${param.flag}">
    <b style="color: red"><c:out value="${param.message}"/></b>
    <br>
    <%--Order information table--%>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.order.criteria"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.requestDate"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.departPlace"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.arrivalPlace"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.startDate"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.endDate"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.distance"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.totalAmount"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.paymentStatus"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.orderStatus"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.adminFullName"/></th>
        </tr>
        <tr>
            <td><textarea name="criteria" rows="4" cols="20">${order.criteria}</textarea></td>
            <td><fmt:formatDate value="${order.requestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <input type="hidden" name="requestDate" value="${order.requestDate}">
            <td><textarea name="departPlace" rows="4" cols="8">${order.departPlace}</textarea></td>
            <td><textarea name="arrivalPlace" rows="4" cols="8">${order.arrivalPlace}</textarea></td>
            <td><input type="datetime-local" name="startDate"
                       value="<fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"></td>
            <td><input type="datetime-local" name="endDate"
                       value="<fmt:formatDate value="${order.endDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"/>"></td>
            <td><input type="text" name="distance" value="${order.distance}" size="3" maxlength="6" minlength="1"/>
            </td>
            <td><input type="text" name="totalAmount" value="${order.totalAmount}" size="3" maxlength="30"
                       minlength="1"/>
            </td>
            <td><input type="text" name="paymentStatus" value="${order.paymentStatus}" size="4" maxlength="30"
                       minlength="1"/></td>

            <td><select name="status">
                <c:forEach var="s" items="${Status.values()}">
                    <c:choose>
                        <c:when test="${order.orderStatus == s}">
                            <option selected value="${s}"><c:out value="${s}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="${s}"><c:out value="${s}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
            <input type="hidden" name="adminName" value="${order.adminName}">
            <input type="hidden" name="adminSurname" value="${order.adminSurname}">
            <td><c:out value="${order.adminName}"/> <c:out value="${order.adminSurname}"/></td>
        </tr>
    </table>
    <br>
    <%--Client information table--%>
    <fmt:message bundle="${loc}" key="table.order.clientLeftInformation"/>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.order.clientFullName"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.phoneNumber"/></th>
        </tr>
        <tr>
            <td><textarea name="clientFullName" rows="4" cols="20">${order.clientFullName}</textarea></td>
            <td><input type="text" name="clientPhone" value="${order.clientPhone}" size="8" maxlength="30"
                       minlength="5"/>
        </tr>
    </table>
    <br>

    <%--Client information from database--%>
    <fmt:message bundle="${loc}" key="table.order.clientInformation"/>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.select"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.name"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.surname"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.phoneNumber"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.additionalInfo"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.status"/></th>
            <th><fmt:message bundle="${loc}" key="table.user.e-mail"/></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><input type="radio" checked name="selectedUser" value="${user.id}"/><c:out value="${c}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.phoneNumber}"/></td>
                <td><c:out value="${user.additionalInfo}"/></td>
                <td><c:out value="${user.status}"/></td>
                <td><c:out value="${user.eMail}"/></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <%--Search client--%>
    <fmt:message bundle="${loc}" key="table.user.name"/>
    <input type="text" name="searchName" placeholder="Ivan" size="10" maxlength="13"/><br>
    <fmt:message bundle="${loc}" key="table.user.surname"/>
    <input type="text" name="searchSurname" placeholder="Ivanov" size="7" maxlength="13"/>
    <button type="submit" name="command" value="SelectCustomerToOrder">
        <fmt:message bundle="${loc}" key="table.search"/>
    </button>
    <br>
    <br>
    <%--Car information table--%>
    <fmt:message bundle="${loc}" key="table.driver.attachedCar"/>
    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th><fmt:message bundle="${loc}" key="table.order.selectCar"/></th>
            <th><fmt:message bundle="${loc}" key="table.order.driverFullName"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.model"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.license"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.color"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.type"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.loadCap"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.passengerCap"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.weelDr"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.odometr"/></th>
            <th><fmt:message bundle="${loc}" key="table.car.status"/></th>
        </tr>

        <c:forEach var="car" items="${cars}">
            <tr>
                <td><input type="radio" checked name="selectedCar" value="${c.id}"/><c:out value="${c}"/></td>
                <td><c:out value="${order.driverName}"/> <c:out value="${order.driverSurname}"/></td>
                <td><c:out value="${car.modelName}"/></td>
                <td><c:out value="${car.licencePlate}"/></td>
                <td><c:out value="${car.color}"/></td>
                <td><c:out value="${car.type}"/></td>
                <td><c:out value="${car.loadCapacity}"/></td>
                <td><c:out value="${car.passengerCapacity}"/></td>
                <td><c:out value="${car.wheelDriveType}"/></td>
                <td><c:out value="${car.odometr}"/></td>
                <td><c:out value="${car.status}"/></td>
            </tr>
        </c:forEach>
    </table>
    <br>

    <%--Select car param--%>
    <fmt:message bundle="${loc}" key="table.order.selectCarModel"/>

    <select name="carType">
        <c:forEach var="carType" items="${carTypes}">
            <option value="${carType}"><c:out value="${carType}"/></option>
        </c:forEach>
    </select>
    <br>
    <br>
    <fmt:message bundle="${loc}" key="table.car.loadCap"/>
    <input type="text" name="loadCapacity" value="" size="8" maxlength="6" minlength="1"/>
    <br>
    <fmt:message bundle="${loc}" key="table.car.passengerCap"/>
    <input type="text" name="passengerCapacity" value="" size="3" maxlength="6" minlength="1"/>
    <br>
    <button type="submit" name="command" value="SelectCarToOrder"><fmt:message bundle="${loc}"
                                                                               key="table.search"/></button>
</form>
<br>
</body>
</html>
