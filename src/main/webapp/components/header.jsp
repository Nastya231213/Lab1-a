<%@page import="com.entity.Genre"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.GenreDAO"%>

<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<style>
.navbar {
	font-family: 'Thelma', sans-serif;
}

.thebtn {
	background-color: #068FFF;
	color: white
}

.btn {
	font-family: 'Thelma', sans-serif;
	color: white;
}
</style>
<div class="container-fluid"
	style="height: 10px; background-color: black"></div>
<div class="container-fluid p-3">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row">
		<div class="col-md-4 text-success">
			<h3 style="font-family: 'Thelma'; color: #4E4FEB">
				<i class="fas fa-dice-d20"></i>Games Collection Shop
			</h3>
		</div>

		<c:if test="${not empty userObj}">
			<div class="col-md-8 text-right">
				<a href="cart.jsp"><i class="fas fa-cart-plus fa-2x "></i></a> <a
					href="login.jsp" class="btn btn-success"
					style="background-color: #4E4FEB;"><i
					class="fas fa-sign-in-alt"></i>${userObj.username}</a> <a
					href="logout" class="btn btn-primary">Logout</a>
			</div>
		</c:if>
		<c:if test="${empty userObj}">
			<div class="col-md-8 text-right">
				<a href="cart.jsp"><i class="fas fa-cart-plus fa-2x "></i></a> <a
					href="login" class="btn btn-success"
					style="background-color: #4E4FEB; color: white"><i
					class="fas fa-user-circle" style="background-color: #4E4FEB;"></i>Login</a>
				<a href="sign_up" class="btn btn-primary "><i
					class="fas fa-user-plus"></i>Register</a>
			</div>
		</c:if>

	</div>


</div>

<nav class="navbar navbar-expand-lg navbar-dark"
	style="background-color: #000000;">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav ">
			<li class="nav-item active"><a class="nav-link"
				href="/GamesCollection/"><i class="fas fa-home"></i>Home <span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item active">
			<li class="nav-item active"><a class="nav-link"
				href="view_games.jsp"><i class="fas fa-gamepad"></i>All Games</a></li>
			<%
			GenreDAO dao = new GenreDAO(DBConnect.getConn());
			List<Genre> list = dao.getAllGenre();
			%>
			<%
			for (Genre g : list) {
			%>
			<li class="nav-item active"><a class="nav-link"
				href="view_games.jsp?genre=<%=g.getId()%>"><%=g.getName()%></a></li>
			<%
			}
			%>


		</ul>
		<c:if test="${not empty userObj}">
		<div class="ml-auto row">


			<form action="settings.jsp" class="form-inline">

				<button class="btn btn-ligth my-2 my-sm-0 ml-1 " type="submit"
					style="color: black;">
					<i class="fas fa-cog" style="color: black;"></i>Setting
				</button></form>
						<form action="settings.jsp" class="form-inline">

</form>
<%dao.closeConnection();%>
		</div>
		</c:if>
	</div>
</nav>