<%@page import="com.DB.DBConnect"%>
<%@page import="com.entity.User"%>
<%@page import="com.dao.UserDAO"%>
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
<%@include file="../components/allCss.jsp"%>
<style>
label {
	font-family: 'Caprasimo';
}
</style>
</head>
<body style="background-color: #EEEEEE;">
<% int userId=Integer.parseInt(request.getParameter("id"));
UserDAO userDAO=new UserDAO(DBConnect.getConn());
User user=userDAO.getUserById(userId);
%>
	<%@include file="header.jsp"%>
	<div class="container p-4">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<form action="edit_user" method="post">
                             <input type="hidden" name="id" value="<%=userId%>">
							<h4 class="text-center" style="font-family: 'Caprasimo'">Edit</h4>


							<div class="form-group">
								<label for="exampleInputEmail1">Username</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									required="required" name="username" value="<%=user.getUsername()%>">

							</div>

							<div class="form-group">

								<label for="exampleInputEmail1">Email</label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required" name="email" value=<%=user.getEmail()%>>

							</div>

							<div class="form-group">
								<div class="form-group">
									<label for="exampleInputEmail1">Enter Full Name</label> <input
										type="text" class="form-control" id="exampleInputEmail1"
										required="required" name="fname" value="<%=user.getFullName()%>">

								</div>

								<label for="exampleInputEmail1">Phone No</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required" name="phno" value="<%=user.getPhone_number()%>">
							</div>

					
					<div class="col-md-13">
						<label class="form-label">Full Address</label>
						<textarea name="address" class="form-control" rows="3"><%=user.getAddress()%></textarea>
					</div>



		
					<div class="text-center">
						<button type="submit" class="btn btn-primary"
							style="font-family: 'Caprasimo'; background-color: #4E4FEB">Edit</button>
<%userDAO.closeConnection(); %>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>