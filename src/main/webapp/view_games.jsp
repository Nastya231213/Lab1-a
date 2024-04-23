<%@page import="com.entity.Platform"%>
<%@page import="com.dao.PlatformDAO"%>
<%@page import="com.dao.GenreDAO"%>
<%@page import="com.entity.Game"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.dao.GameDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../components/allCss.jsp"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">
<style>
p {
	font-family: 'Thelma';
}

.rounded-card {
	border-radius: 10px;
	box-shodow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}

label {
	font-family: 'Thelma';
}

select {
	font-family: 'Thelma';
}

select option {
	font-family: 'Thelma';
}
</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="../components/header.jsp"%>

	<%
	String page1 = request.getParameter("page");
	if (page1 == null) {
		page1 = "1";
	}
	PlatformDAO daoPlatform = new PlatformDAO(DBConnect.getConn());

	GameDAO dao1 = new GameDAO(DBConnect.getConn());
	GenreDAO daoGenre = new GenreDAO(DBConnect.getConn());

	List<Game> gamesList = dao1.getAllGames();
	int paramGenre = -1;
	String genre_ = request.getParameter("genre");
	if (genre_ != null) {
		paramGenre = Integer.parseInt(request.getParameter("genre"));
		gamesList = dao1.getGameByGenreId(paramGenre);
	}

	int quantityPerPage = 8;
	int quantityOfPages = gamesList.size() / 8;
	if (gamesList.size() % 8 != 0) {
		quantityOfPages++;
	}
	%>
	<h1 class="text-center p-3"
		style="color: black; font-family: 'Thelma', sans-serif;">All
		Games</h1>

	<div class="container p-4">
		<div class="col-md-12">
			<div class="card rounded-card">
				<div class="card-body">
					<form action="search">

						<div class="row">
							<%
							if (genre_ == null) {
							%>
							<label for="" class="mr-1">Genre:</label> <select name="genre">
								<option class="custom-font" value="0">Select...</option>

								<%
								for (Genre genre1 : list) {
								%>
								<option value=<%=genre1.getId()%>><%=genre1.getName()%></option>
								<%
								}
								%>
							</select>
							<%
							} else {
							%>
							<input type="hidden" name="genre" value="<%=genre_%>" />
							<%
							}
							%>
							<%
							PlatformDAO platformDAO = new PlatformDAO(DBConnect.getConn());
							List<Platform> listPlatform = platformDAO.getAllPlatforms();
							int start;
							if (page1 == "1") {
								start = 0;
							} else {
								start = (Integer.parseInt(page1) - 1) * 8;
							}
							int end = start + 8;
							if ((gamesList.size() - end) < 0) {
								end = gamesList.size();
							}
							int max = 0;
							for (int i = start; i < end; i++) {
								Game g = gamesList.get(i);
								if (max < g.getPrice()) {
									max = (int) (double) g.getPrice();
								}
							}
							%>
							<label for="" class="mr-1 ml-1">Platform:</label> <select
								name="pl">
								<option class="custom-font" value="0">Select...</option>

								<%
								for (Platform platform : listPlatform) {
								%>

								<option class="custom-font" value="<%=platform.getId()%>"><%=platform.getName()%></option>
								<%
								}
								%>
							</select> <label for="" class="mr-1 ml-4">Min Price</label> <input
								type="number" name="minPrice" value="0"> <label for=""
								class="ml-1 mr-1">Max Price</label> <input type="number"
								name="maxPrice" value="<%=max%>">
						</div>
						<div class="row mt-3">
							<div class="form-inline my-2 my-lg-0 justify-content-start">
								<input class="form-control mr-sm-2"
									style="font-family: 'Thelma';" name="keyword" type="search"
									placeholder="Search" aria-label="Search">
								<button class="btn my-2 my-sm-0" style="background-color: black"
									type="submit">Search</button>
							</div>

						</div>
					</form>
				</div>


			</div>

		</div>
	</div>


	<div class="container p-1">
		<c:if test="${not empty genre_}">
			<h1 class="text-center"
				style="color: black; font-family: 'Thelma', sans-serif;"><%=dao.getGenreById(paramGenre).getName()%></h1>
		</c:if>
		<c:if test="${empty genre_}">

		</c:if>
		<div class="row p-3">
			<%
			for (int i = start; i < end; i++) {
				Game g = gamesList.get(i);
			%>
			<div class="col-md-3 mb-4">
				<div class="card rounded-card">
					<div class="card-body text-center">

						<img src="image/<%=g.getPhotoName()%>"
							style="width: 150px; height: 220px;"> <br> <br>
						<p><%=g.getTitle()%></p>
						<%
						String nameGenre = daoGenre.getGenreById(g.getGenre_id()).getName();
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
								style="background-color: #4E4FEB; color: white;"><i
								class="fas fa-cart-plus"></i> Add Cart</a> <a href="view_details.jsp?id=<%=g.getId()%>"
								class="btn btn-sm ml-1"
								style="background-color: #4E4FEB; color: white;">View
								Details</a>
						</div>


					</div>

				</div>
			</div>
			<%
			}
			%>

		</div>
		<div>

			<%
			int page_ = Integer.parseInt(page1);
			int previous = page_;
			int next = page_;
			if (!page1.equals("1")) {
				previous = page_ - 1;

			}
			if (page_ != gamesList.size()) {
				next = page_ + 1;
			}
			%>

			<nav aria-label="Page navigation example">
				<ul class="pagination ml-3">
					<li class="page-item"><a class="page-link"
						href="view_games.jsp?page=<%=previous%>">Previous</a></li>
					<%
					for (int i = 1; i <= quantityOfPages; i++) {
					%>
					<li class="page-item"><a class="page-link"
						href="view_games.jsp?page=<%=i%>"><%=i%></a></li>

					<%
					}
					%>
					<li class="page-item"><a class="page-link"
						href="view_games.jsp?page=<%=next%>">Next</a></li>
				</ul>
			</nav>
		</div>
	</div>
	<%
	daoGenre.closeConnection();
	dao1.closeConnection();
	daoPlatform.closeConnection();
	%>
</body>
</html>