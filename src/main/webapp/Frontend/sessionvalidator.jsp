
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.*,java.io.*,java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<c:if test="${empty sessionScope.loggedInEmployee}">
    <c:redirect url="./home"/>
</c:if>

<!-- Your HTML content goes here -->

</body>
</html>
