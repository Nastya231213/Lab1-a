<%@page import="com.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%@include file="components/allCss.jsp"%>
<style>
label {
	font-family: 'Caprasimo';
}
</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="components/header.jsp"%>
	<div class="container p-4">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<form action="edit_theuser" method="post">
                      <% User u=(User)session.getAttribute("userObj");
                      
                      %>
							<h4 class="text-center" style="font-family: 'Caprasimo'">Edit Profile</h4>

							<br>

							<div class="form-group">
								<label for="exampleInputEmail1">Username</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									required="required" name="username" value="<%=u.getUsername()%>">

							</div>

							<div class="form-group">

								<label for="exampleInputEmail1">Email</label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required" name="email" value="<%=u.getEmail()%>">

							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Old Password</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									required="required" name="password">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">New Password</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									required="required" name="newPassword">
							</div>

							<div class="form-group">
								<div class="form-group">
									<label for="exampleInputEmail1">Full Name</label> <input
										type="text" class="form-control" id="exampleInputEmail1"
										required="required" name="fname" value="<%=u.getFullName()%>">

								</div>

								<label for="exampleInputEmail1">Phone No</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required" name="phno" value="<%=u.getPhone_number()%>">
							</div>

					
					<div class="col-md-13">
						<label class="form-label">Full Address</label>
						<textarea name="address" class="form-control" rows="3"><%=u.getAddress()%></textarea>
					</div>



					<div class="text-center">
						<button type="submit" class="btn btn-primary"
							style="font-family: 'Caprasimo'; background-color: #4E4FEB">Edit</button>

					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>