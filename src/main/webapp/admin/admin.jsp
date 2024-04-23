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

h4 {
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

	<%@include file="header.jsp"%>

	<c:if test="${empty adminObj}">
		<c:redirect url="../login.jsp" />
	</c:if>
	<br>
	<h2 class="text-center">Welcome, Admin!</h2>


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
	<div class="container p-3">
		<div class="row">
			<div class="col-md-6 ">
				<a href="addGame" style="color: #068FFF;">
					<div class="card rounded-card shadow">
						<div class="card-body">
							<i class="fas  fa-gamepad fa-3x " style="color: #068FFF;"></i><br>
							<h4>Add Game</h4>
						</div>

					</div>
				</a>


			</div>
			<div class="col-md-6">
				<a href="" style="color: #4E4FEB" data-toggle="modal"
					data-target="#addGenreModal">
					<div class="card rounded-card shadow">
						<div class="card-body">
							<i class="fas fa-tape fa-3x" style="color: #4E4FEB;"></i><br>
							<h4>Add Genre</h4>
						</div>

					</div>
				</a>
			</div>


			<div class="col-md-3 mt-3">
				<a href="" style="color: #A66CFF;" data-toggle="modal"
					data-target="#addPlatformModal">
					<div class="card rounded-card shadow">
						<div class="card-body">
							<i class="fas fa-window-restore fa-3x" style="color: #A66CFF;"></i><br>
							<h4>Add Platform</h4>
						</div>

					</div>
				</a>


			</div>

			<div class="col-md-3 mt-3">
				<a href="users.jsp" style="color: #6A0DAD;" ">
					<div class="card rounded-card shadow">
						<div class="card-body">
							<i class="fas fa-users fa-3x" style="color: #6A0DAD;"></i><br>
							<h4>Users</h4>
						</div>

					</div>
				</a>


			</div>
			<div class="col-md-3 mt-3">
				<a href="orders.jsp" style="color: #2B65EC;">
					<div class="card rounded-card shadow">
						<div class="card-body">
							<i class="fas fa-shopping-basket fa-3x" style="color: #2B65EC;"></i><br>
							<h4>Orders</h4>
						</div>

					</div>
				</a>

			</div>
									<div class="col-md-3 mt-3">
				<a href="../admin_logout" style="color:#5837D0" data-toggle="modal"
					data-target="">
					<div class="card rounded-card shadow">
						<div class="card-body">
							<i class="fas fa-sign-out-alt fa-3x" style="color: #5837D0;"></i><br>
							<h4>Logout</h4>
						</div>

					</div>
				</a>


			</div>
			
		</div>
	</div>
	<div class="modal" id="addGenreModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title " style="font-family: 'Thelma', sans-serif">
						<i class="fas fa-tape f"></i>Add Genre
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="../addGenre" method="post">
						<div class="form-group">
							<label style="font-family: 'Thelma', sans-serif">Enter
								Genre Name:</label> <input type="text" name="genre" class="form-control"
								placeholder="Genre name">
						</div>

						<br>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Add</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="addPlatformModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title " style="font-family: 'Thelma', sans-serif">
						<i class="fas fa-window-restore"></i>Add a platform
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="../addPlatform" method="post">
						<div class="form-group">
							<label style="font-family: 'Thelma', sans-serif">Enter
								Platform Name:</label> <input type="text" name="platform"
								class="form-control" placeholder="Platform name">
						</div>

						<br>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Add</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>