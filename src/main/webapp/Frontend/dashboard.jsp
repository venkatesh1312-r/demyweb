<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
.header .logo img {
	width: 100%;
	height: 7vh;
	top: 0;
	left: 0;
}

#services {
	margin-top: -90px;
}

.service {
	padding: 32px;
	background-color: #fff;
	box-shadow: var(--shadow);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	/* horizontal-offset vertical-offset blur-radius color */
	border: 1px solid white;
	border-radius: 10px;
	margin: 10px;
	height: 40vh;
}

.service h5 {
	margin-bottom: 14px;
}

.service img {
	width: 90px;
}
</style>
</head>
<body>

	<%@ include file="employeeheader.jsp"%>


	<section id="home" class="home">
		<h2>Home / Dashboard</h2>
	</section>

	<br>
	<br>
	<br>

	<section id="about" class="about">
		<h1 class="heading">Welcome Back
			${sessionScope.loggedInEmployee.getName()} !</h1>

	</section>


	<section id="services" class="text-center" style="margin-top: -190px;">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="intro">
						<h1 class="heading">What we Offer ?</h1>
						<p class="mx-auto text-bold">Demy Software Solutions (DSS)
							provides a comprehensive range of services to meet the diverse
							needs of our clients. We go beyond traditional offerings to
							ensure a holistic learning and growth experience.</p>
					</div>
				</div>
			</div>
			<div class="row g-4">
				<div class="col-lg-4 col-md-6">
					<div class="service">
						<img src="https://cdn-icons-png.flaticon.com/512/4974/4974985.png"
							alt="staff icon"><br>
						<br>
						<h5>Staff Count</h5>
						<h1>${employeeCount}</h1>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="service">
						<img
							src="https://hrone.cloud/wp-content/uploads/2021/05/leave-management-1.png"
							alt=""
							style="transform: scale(1.6); border-radius: 900px; background: white;"><br>
						<br>
						<br>
						<br>
						<h5>Leaves</h5>
						<h1>${leavesAppliedCount}/${leavesApprovedCount}</h1>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="service">
						<img src="https://cdn-icons-png.flaticon.com/512/9291/9291935.png"
							alt=""><br>
						<br>
						<br>
						<h5>Get Touch/Careers</h5>
						<h1>${getTouchCount}/${careerCount}</h1>
					</div>
				</div>



			</div>
		</div>
	</section>
	<br>
	<br>
	<br>
	<br>




	<%@ include file="footer.jsp"%>


</body>
</html>