<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
<style type="text/css">
.error {
  color: red;
}
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
        <h2>Home / Leave Form</h2>
    </section>

   
<section id="contact" class="contact">
        <h1 class="heading">Apply Leave</h1>
    </section>
    
    
  <div class="contact-in">
    <div class="contact-map">
        <img src="./images/pexels-andrea-piacquadio-845434.jpg" class="img-fluid" alt="Team member 1">
    </div>
    <div class="contact-form d-flex justify-content-center" style="margin-top: 0%">
    
        <form action="/applyLeave" method="POST">
        
         <span style="color: green; text-align: center; margin:0px 30px;">${msg}</span><br><br><br>
    
    <span style="color: red; text-align: center; margin:0px 30px;">${Errormsg}</span><br><br><br>

    <input type="text" name="EmployeeName" placeholder="Name" class="contact-form-email" value="${sessionScope.loggedInEmployee.getName()}" readonly>
    
    <input type="email" name="EmployeeEmail" placeholder="Email" class="contact-form-email" value="${sessionScope.loggedInEmployee.getEmail()}" readonly>
    
    <input type="text" name="EmployeeRole"" placeholder="Role" class="contact-form-email" value="${sessionScope.loggedInEmployee.getRole()}" readOnly><br>

    <!-- Start Date -->
<label for="startdate" class="px-5 text-danger">Start Date:</label>
<input type="date" id="startdate" name="startDate" class="contact-form-email" required ><br>
                
<!-- End Date -->
<label for="enddate" class="px-5 text-danger">End Date:</label>
<input type="date" id="enddate" name="endDate" class="contact-form-email" required><br>

    

   <textarea rows="90" cols="130" placeholder="Leave Reason" name="Reason" class="contact-form-email">
Applying Leave Reason 
</textarea>

    <!-- Adjusted name attribute -->
    <input type="submit" value="Apply" name="submit" class="contact-form-btn">
    
</form>

    </div>
</div>


   <%@ include file="footer.jsp" %>
</body>
</html>