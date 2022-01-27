<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.message" var="message"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
             var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
             var="en_button"/>
<form action="" method="get">
    <input type="hidden" name="command" value="ChangeLocalization">
    <input type="hidden" name="prev_command" value="${param.command}">
    <div style="float: right; padding: 5px; text-align: right;">
        <form action="welcome" method="get">
            <button type="submit" name="locale" value="ru">${ru_button}</button>
            <button type="submit" name="locale" value="eng">${en_button}</button>
            <br/>
    </div>
</form>
<c:out value="${message}"/>
<fmt:message bundle="${loc}" key="local.message"/>
</body>
</html>
