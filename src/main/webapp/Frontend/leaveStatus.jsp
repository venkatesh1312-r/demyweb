<%@page import="com.demy.Entites.EmployeeEntity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>

.header .logo img{
    width: 100%;
    height: 7vh;
    top: 0;
    left: 0;
}

#services
{
margin-top: -90px;
}
.service {
    padding: 32px;
    background-color: #fff;
    box-shadow: var(--shadow);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); /* horizontal-offset vertical-offset blur-radius color */
    border: 1px solid white;
    border-radius:10px;
    margin: 10px;
    height: 40vh;
}

.service h5 {
    margin-bottom: 14px;
}

.service img {
    width: 90px;
}
table,td,a
{
font-size: 120%;
    text-transform:none;

}

</style>
</head>
<body>

   <%@ include file="employeeheader.jsp" %>
   
   
 <section id="home" class="home">
        <h2>Home / Leaves Status</h2>
    </section>

<br>
<br>
<br>
<div class="container-fluid">
    <h2 class="text-center">Leave Details</h2>
    <span style="color: green; text-align: center; margin:0px 30px;">${msg}</span><br><br><br>

    <div class="table-responsive">
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Employee Name</th>
                <th>Employee Email</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Reason</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="leave" items="${leavesStatus}">
                <tr>
                    <td>${leave.id}</td>
                    <td>${leave.employeeName}</td>
                    <td>${leave.employeeEmail}</td>
                    <td>${leave.startDate}</td>
                    <td>${leave.endDate}</td>
                    <td>${leave.reason}</td>
                    <td>
                        <c:choose>
                            <c:when test="${leave.status eq 3}">
                                Rejected
                            </c:when>
                            <c:when test="${leave.status eq 2}">
                                Approved
                            </c:when>
                            <c:when test="${leave.status eq 0}">
                                Not Approved
                            </c:when>
                            <c:otherwise>
                                Approved By HR
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</div>
<%@ include file="footer.jsp" %>
</body>
</html>
