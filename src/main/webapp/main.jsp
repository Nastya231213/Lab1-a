<%@page import="com.dao.ReviewDAO"%>
<%@page import="com.dao.PlatformDAO"%>
<%@page import="com.entity.Game"%>
<%@page import="com.dao.GameDAO"%>
<%@page import="com.DB.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Games Collection!</title>
<%@include file="components/allCss.jsp"%>
<style type="text/css">
p {
	font-family: 'Thelma';
}

.back-img {
	background: url("image/game.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
	width: 100%;
	height: 40vh;
	border-bottom: 5px solid #000;
}
a:hover{

color:#E0FFFF;
}

</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="components/header.jsp"%>
	<div class="container-fluid back-img"></div>
	<div class="container p-4 ">
		<h1 class="text-center" style="font-family: 'Thelma';">
		Recent Games
		</h1>
			<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
			<c:if test="${not empty succMsg}">
							<p class="text-center text-success">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>
			<div class="row p-3">
			<%
			GameDAO daoGame = new GameDAO(DBConnect.getConn());

			GenreDAO daoGenre = new GenreDAO(DBConnect.getConn());
			ReviewDAO reviewDAO = new ReviewDAO(DBConnect.getConn());

			List<Game> listGame = daoGame.getAllRecentGames();
			for (Game g : listGame) {
			%>
			<div class="col-md-3">
				<div class="card rounded-card">
					<div class="card-body text-center">
						<img src="image/<%=g.getPhotoName()%>"
							style="width: 150px; height: 220px;"> <br> <br>
							
							<%
				String rating = reviewDAO.getRatingString(g.getId());
				%>
						<p><%=g.getTitle()%></p>
						<%
						String nameGenre = daoGenre.getGenreById(g.getGenre_id()).getName();
						PlatformDAO daoPlatform = new PlatformDAO(DBConnect.getConn());
						String namePlatform = daoPlatform.getPlatformById(g.getPlatform_id()).getName();
						%>
						<div align="left">
						<p>
							Rating:<c:forTokens items="<%=rating%>" delims="," var="star">
									<c:if test="${star eq 'on' }">
										<img src="image/rating-on.png" />
									</c:if>
									<c:if test="${star eq 'off'}">
										<img src="image/rating-off.png" />
									</c:if>
									<c:if test="${star eq 'half'}">
									<img src="image/rating-half.png"/>
									</c:if>
								</c:forTokens>				
						</p>
							<p>
								Genre:
								<%=nameGenre%></p>
							<p>
								Platform:
								<%=namePlatform%></p>
							<p>
								Price: <i class="fas fa-dollar-sign"></i><%=g.getPrice()%></p>
							<p>
								Published year:
								<%=g.getYear()%></p>
						</div>
			<div align="center" class="row">


							<a href="addToCart?gid=<%=g.getId()%>" class="btn  btn-sm ml-1"
								style="background-color: #4E4FEB"><i
								class="fas fa-cart-plus"></i> Add Cart</a> <a href="view_details.jsp?id=<%=g.getId()%>"
								class="btn btn-sm ml-1" style="background-color: #4E4FEB">View
								Details</a>
						</div>
	</div>

	</div>
	</div>
	<%
	}
	%>

	</div>

	<div class="text-center mt-1">
		<a href="view_games.jsp" class="btn btn-sm" style="background-color: #4E4FEB">View
			All</a>
	</div>

	</div>
	<hr>
	<div class="container p-4">
		<h1 class="text-center"
			style="color: black; font-family: 'Thelma', sans-serif;">New
			Games</h1>
		<div class="row p-3">
			<%
			List<Game> listGame1 = daoGame.getAllNewGames();
			for (Game g : listGame1) {
			%>
			<div class="col-md-3">
				<div class="card rounded-card">
					<div class="card-body text-center">
						<img src="image/<%=g.getPhotoName()%>"
							style="width: 150px; height: 220px;"> <br> <br>
						<p><%=g.getTitle()%></p>
						<%
						String nameGenre = daoGenre.getGenreById(g.getGenre_id()).getName();
						PlatformDAO daoPlatform = new PlatformDAO(DBConnect.getConn());
						String namePlatform = daoPlatform.getPlatformById(g.getPlatform_id()).getName();
						%>
						<div align="left">
							<p>
								Genre:
								<%=nameGenre%></p>
							<p>
								Platform:
								<%=namePlatform%></p>
							<p>
								Price: <i class="fas fa-dollar-sign"></i><%=g.getPrice()%></p>
							<p>
								Published year:
								<%=g.getYear()%></p>


						</div>
						<div align="center" class="row">


							<a href="addToCart?gid=<%=g.getId()%>" class="btn  btn-sm ml-1"
								style="background-color: #4E4FEB"><i
								class="fas fa-cart-plus"></i> Add Cart</a> <a
								href="view_details.jsp?id=<%=g.getId()%>"
								class="btn btn-sm ml-1" style="background-color: #4E4FEB">View
								Details</a>
						</div>


					</div>

				</div>
			</div>
			<%
			}
			%>

		</div>
		<div class="text-center mt-1">
			<a href="view_games.jsp" class="btn btn-sm" style="background-color: #4E4FEB">View
				All</a>
		</div>

	</div>
<%daoGame.closeConnection(); 
daoGenre.closeConnection(); 
reviewDAO.closeConnection();
%>

</body>
</html>