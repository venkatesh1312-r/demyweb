<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>">
    <script src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'/>"></script>
    <link rel="icon" href="<c:url value='images/image.png'/>">
    <link rel="stylesheet" href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css'/>">
    <link rel="stylesheet" href="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css'/>" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'/>" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="<c:url value='lib/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='lib/ionicons/css/ionicons.min.css'/>" rel="stylesheet">
    <link href="<c:url value='lib/owlcarousel/assets/owl.carousel.min.css'/>" rel="stylesheet">
    <link href="<c:url value='lib/lightbox/css/lightbox.min.css'/>" rel="stylesheet">
    
    <script src="<c:url value='./js/main.js'/>"></script>

    <!-- Additional Scripts -->
    <script src="<c:url value='https://code.jquery.com/jquery-3.5.1.slim.min.js'/>" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js'/>" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script src="<c:url value='lib/jquery/jquery.min.js'/>"></script>
    <script src="<c:url value='lib/jquery/jquery-migrate.min.js'/>"></script>
    <script src="<c:url value='lib/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
    <script src="<c:url value='lib/easing/easing.min.js'/>"></script>
    <script src="<c:url value='lib/waypoints/waypoints.min.js'/>"></script>
    <script src="<c:url value='lib/counterup/counterup.min.js'/>"></script>
    <script src="<c:url value='lib/owlcarousel/owl.carousel.min.js'/>"></script>
    <script src="<c:url value='lib/lightbox/js/lightbox.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='./css/careers.css'/>"/>

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
<header class="header">
        <a href="#" class="logo"><img src="<c:url value='./images/logo.png'/>" alt="demy logo"></a>
        <div class="fas fa-bars"></div>
        <nav class="navbar">
            <ul>
                <li><a href="./home">home</a></li>
                <li><a href="#about">about</a></li>
                <li><a href="#service">services</a></li>
                <li><a href="#portfolio">portfolio</a></li>
                <li><a href="#team">team</a></li>
                <li><a href="./careers">career</a></li>
                <li><a href="#contact">contact</a></li>
                <li><a href="#faq">FAQ</a></li>
                <li><a href="./employeeLogin" class="btn btn-danger">Employee Login</a></li>
                
            </ul>
        </nav>
    </header>

 <section id="home" class="home">
        <h2>Home / Forgot Password</h2>
    </section>
   
   
   <section id="career-heading" class="career-heading">
        <h1 class="heading">Login</h1>
        <p>Job Opening in IT Company. Apply Now!</p>
    </section>
    
    
  <div class="contact-in">
    <div class="contact-map">
        <img src="./images/pexels-andrea-piacquadio-845434.jpg" class="img-fluid" alt="Team member 1">
    </div>
    <div class="contact-form d-flex justify-content-center" style="margin-top: 10%">
        <form action="/otpVerify" method="POST">
        
            <span style="color: green; text-align: center; margin:90px 30px;">${msg}</span><br><br><br>
          
<input type="password" name="otp" placeholder="OTP" class="contact-form-email" pattern="\d{1,4}" />
   
<input type="submit" value="Submit" name="submit" class="contact-form-btn">
                  &nbsp;&nbsp;&nbsp;<a href="./forgotPassword">Back</a>

        </form>
    </div>
</div>



    


      
   <%@ include file="footer.jsp" %>
</body>
</html>