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
        <h2>Home / Careers</h2>
    </section>

<br>
<br>
<br>
<div class="container-fluid">
    <h2 class="text-center">Careers Details</h2>
    <span style="color: green; text-align: center; margin:0px 30px;">${msg}</span><br><br><br>

    <div class="table-responsive">
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Status</th>
                <th>Experience</th>
                <th>Resume</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="career" items="${careers}">
                <tr>
                    <td>${career.id}</td>
                    <td>${career.name}</td>
                    <td>${career.status}</td>
                    <td>${career.experience}</td>
                    <td><a href="/download/${career.resumePath}" class="btn btn-success">Download File</a></td>
                    <td><a href="viewCareerDetails?id=${career.id}" class="btn btn-primary">View Details</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</div>
<%@ include file="footer.jsp" %>
</body>
</html>
