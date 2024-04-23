<%@page import="com.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<%@include file="../components/allCss.jsp"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">
<style>
h2 {
	font-family: 'Thelma', sans-serif;
}

h3 {
	font-family: 'Thelma', sans-serif;
}


    .rounded-card {
      border-radius: 10px; 
      	box-shodow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
      
    }
a:hover {
	text-decoration: none;
}
.shadow{
box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);}
</style>
</head>
<body style="background-color: #EEEEEE;">

	<%@include file="components/header.jsp"%>
<
	<c:if test="${not empty succMsg}">
		<p class="text-center "
			style="font-family: 'Thelma', sans-serif; color: #03C988">${succMsg}</p>
		<c:remove var="succMsg" scope="session" />

	</c:if>
	<c:if test="${not empty errorMsg}">
		<p class="text-center"
			style="font-family: 'Thelma', sans-serif; color: #4E4FEB">${errorMsg}</p>
		<c:remove var="errorMsg" scope="session" />

	</c:if>
	<br>
	
	<div class="container p-3 text-center">
		<div class="row">
			<div class="col-md-6" >
				<a href="edit_profile.jsp" style="color: #068FFF;">
					<div class="card rounded-card shadow"  style="height: 190px;">
						<div class="card-body">
							<i class="fas fa-edit fa-4x" style="color: #068FFF;"></i><br>
							<h3>Edit Profile</h3>
						</div>

					</div>
				</a>


			</div>
			<div class="col-md-6 text-center">
				<a href="orders.jsp?id=${userObj.user_id}" style="color: #4E4FEB">
					<div class="card rounded-card shadow" style="height: 190px;">
						<div class="card-body">
							<i class="fas fa-user-circle fa-4x" style="color: #4E4FEB;"></i><br>
							<h3>My Orders</h3>
						</div>

					</div>
				</a>
			</div>
			


			</div>
		</div>
	</div>
</body>
</html>