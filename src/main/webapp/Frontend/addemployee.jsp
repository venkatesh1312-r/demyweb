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
        <h2>Home / Add Employee</h2>
    </section>

   
<section id="contact" class="contact">
        <h1 class="heading">Add Employee</h1>
        <p>Job Opening in IT Company. Apply Now!</p>
    </section>
    
    
  <div class="contact-in">
    <div class="contact-map">
        <img src="./images/pexels-andrea-piacquadio-845434.jpg" class="img-fluid" alt="Team member 1">
    </div>
    <div class="contact-form d-flex justify-content-center" style="margin-top: 0%">
        <form action="./addEmployee" method="POST" enctype="multipart/form-data">
    <span style="color: green; text-align: center; margin:0px 30px;">${msg}</span><br><br><br>
    <span style="color: red; text-align: center; margin:0px 30px;">${Errormsg}</span><br><br><br>

    <input type="text" name="name" placeholder="Name" class="contact-form-email" required>
    <input type="email" name="email" placeholder="Email" class="contact-form-email" required>
    <input type="password" name="password" placeholder="Password" class="contact-form-email" required>
<input type="text" name="role" id="role" placeholder="Role" class="contact-form-email" required onchange="validateRole(this)"><br>
    <input type="file" name="offerLetter" class="contact-form-email" required> <!-- Adjusted name attribute -->
    <input type="submit" value="Add" name="submit" class="contact-form-btn">
</form>

    </div>
</div>



    <script>
function validateRole(input) {
    var role = input.value.trim();

    // Convert the role to lowercase for case-insensitive comparison
    var lowercaseRole = role.toLowerCase();

    // Check if the role contains "manager" in any case
    if (lowercaseRole.includes("manager")) {
        alert("Role cannot contain 'Manager'.");
        // Clear the value of the input field
        input.value = "";
    }
}
</script>
   <%@ include file="footer.jsp" %>
</body>
</html>