<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'/>">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'/>">
    
    <!-- Your custom CSS -->
    <link rel="stylesheet" href="<c:url value='css/style.css'/>">
    
    <!-- Favicon -->
    <link rel="icon" href="<c:url value='images/image.png'/>">
    
    <!-- Bootstrap JS and jQuery (required for dropdown functionality) -->
    <script src="<c:url value='https://code.jquery.com/jquery-3.5.1.slim.min.js'/>"></script>
    <script src="<c:url value='https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js'/>"></script>
    
    <!-- Your custom JavaScript -->
    <script src="<c:url value='./js/main.js'/>"></script>
    
    <title>Dropdown Menu</title>
</head>
<body>
    <header class="header">
        <a href="#" class="logo"><img src="<c:url value='./images/logo.png'/>" alt="demy logo"></a>
        <div class="fas fa-bars"></div>
        <nav class="navbar">
            <ul>
                <li><a href="./dashboard">Home</a></li>
                <li><a href="./staff">Staff</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Leave
                    </a>
                    <div class="dropdown-menu text-dark" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item text-dark" href="/applyLeave">Apply</a>
                        <a class="dropdown-item text-dark" href="/leaveStatus">Status</a>
                        
                      <c:if test="${sessionScope.loggedInEmployee.getRole().toLowerCase() eq 'manager' || sessionScope.loggedInEmployee.getRole().toLowerCase() eq 'hr'}">
    <a class="dropdown-item text-dark" href="/approveLeave">Approve</a>
</c:if>

                    </div>
                </li>
                           
                <li><a href="./viewCareers">Career</a></li>
                <li><a href="./logout" class="btn btn-danger">Logout</a></li>
            </ul>
        </nav>
    </header>
    
    
<c:if test="${empty sessionScope.loggedInEmployee}">
    <c:redirect url="./home"/>
</c:if>
    
</body>
</html>
