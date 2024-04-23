<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">


<%@include file="components/allCss.jsp"%>
<style>
label {
	font-family: 'Thelma';
}
p{
	font-family: 'Thelma';

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
						<form action="sign_up" method="post">

							<h4 class="text-center" style="	font-family: 'Thelma';
">Registration
								Page</h4>
							<c:if test="${not empty succMsg}">
								<p class="text-center "
									style="color: #4E4FEB">${succMsg}</p>
								<c:remove var="succMsg" scope="session" />

							</c:if>
							<c:if test="${not empty errorMsg}">
								<p class="text-center text-danger"
									>${errorMsg}</p>
								<c:remove var="errorMsg" scope="session" />
							</c:if>

							<c:if test="${not empty failMsg}">
								<p class="text-center text-danger">${failMsg}</p>
								<c:remove var="failMsg" scope="session" />
							</c:if>
							<c:if test="${not empty pasMsg}">
								<p class="text-center text-danger">${pasMsg}</p>
								<c:remove var="pasMsg" scope="session" />
							</c:if>
							<c:if test="${not empty passAlreadyExistMsg}">
								<p class="text-center text-danger">${passAlreadyExistMsg}</p>
								<c:remove var="passAlreadyExistMsg" scope="session" />
							</c:if>
							<br>

							<div class="form-group">
								<label for="exampleInputEmail1">Enter Username</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									required="required" name="username">

							</div>

							<div class="form-group">

								<label for="exampleInputEmail1">Email address</label> <input
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required" name="email">

							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									required="required" name="password">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Confirm Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									required="required" name="confirmPassword">
							</div>

							<div class="form-group">
								<div class="form-group">
									<label for="exampleInputEmail1">Enter Full Name</label> <input
										type="text" class="form-control" id="exampleInputEmail1"
										required="required" name="fname">

								</div>

								<label for="exampleInputEmail1">Phone No</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required" name="phno">
							</div>

					
					<div class="col-md-13">
						<label class="form-label">Full Address</label>
						<textarea name="address" class="form-control" rows="3"></textarea>
					</div>



					<div class="form-check text-center p-2">
						<input type="checkbox" class="form-check-input" name="check"
							id="exampleCheck1"> <label class="form-check-label"
							for="exampleCheck1">Agree terms & Condition</label>
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary"
							style="	font-family: 'Thelma';
; background-color: #4E4FEB">Sign
							Up</button>

					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>