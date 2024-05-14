<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
<style type="text/css">

.home{
    min-height: 70vh;
    width: 100vw;
    background-image: url(../images/img3.jpg);
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-flow: column;
    text-align: center;
    padding: 0 1rem;
    position: relative;
    overflow: hidden !important;
}
 input
        {
        text-transform: none;
        }
</style>
</head>
<body>
 <%@ include file="employeeheader.jsp" %>
   
   
 <section id="home" class="home">
        <h2>Home / Update Employee</h2>
    </section>

   
<section id="contact" class="contact">
        <h1 class="heading">Update Employee</h1>
        <p>Job Opening in IT Company. Apply Now!</p>
    </section>
    
    
  <div class="contact-in">
    <div class="contact-map">
        <img src="./images/pexels-andrea-piacquadio-845434.jpg" class="img-fluid" alt="Team member 1">
    </div>
    <div class="contact-form d-flex justify-content-center" style="margin-top: 0%">
        <form action="./edit" method="POST" modelAttribute="emp">
    <span style="color: green; text-align: center; margin:0px 30px;">${msg}</span><br><br><br>
    <span style="color: red; text-align: center; margin:0px 30px;">${Errormsg}</span><br><br><br>

    <input type="text" name="id" placeholder="id" class="contact-form-email" value="${emp.id}" required readonly style="background:lightblue">

    <input type="text" name="name" placeholder="Name" class="contact-form-email" value="${emp.name}" required>

    <input type="email" name="email" placeholder="Email" class="contact-form-email" value="${emp.email}" required readonly style="background:lightblue">

    <input type="password" name="password" placeholder="Password" class="contact-form-email" value="${emp.password}" required>

    <input type="text" name="role" placeholder="Role" class="contact-form-email" value="${emp.role}" readOnly required style="background:lightblue">

    

    <input type="submit" value="Update" name="submit" class="contact-form-btn">
</form>

    </div>
</div>



    


      
   <%@ include file="footer.jsp" %>
</body>
</html>