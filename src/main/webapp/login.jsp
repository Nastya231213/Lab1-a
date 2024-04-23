<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@include file="components/allCss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=Caprasimo'
	rel='stylesheet'>
<style>

label{
	font-family: 'Thelma', sans-serif;

}
p{
	font-family: 'Thelma', sans-serif;

}
</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="components/header.jsp"%>
	<div class="container p-4">
		<div class="row mt-2">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center" style="font-family: 'Thelma', sans-serif">Login</h2>
                              
						<c:if test="${not empty succMsg}">
							<p class="text-center text-success">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>
					</div>
					<form action="login" method="post">
							<div class="form-group col-md-12">
								<label for="exampleInputEmail1">Email address</label> <input class="form-control"
									type="email" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="email" required>

							</div>		
							<div class="form-group col-md-12">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" name="password" required> 
							</div>
							<div class="text-center">
							
							<button type="submit" class="btn btn-primary" 
							style="font-family: 'Thelma', sans-serif; background-color: #4E4FEB">Sign In </button>
							</div>
					</form>
					<br>
				</div>

			</div>

		</div>
	</div>
</body>
</html>