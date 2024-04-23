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
	GameDAO dao1 = new GameDAO(DBConnect.getConn());
	GenreDAO daoGenre = new GenreDAO(DBConnect.getConn());
	PlatformDAO daoPlatform = new PlatformDAO(DBConnect.getConn());

	int paramGenre = -1;
	List<Game> listOfGames = (List<Game>) session.getAttribute("listGames");
	session.removeAttribute("listGames");
	%>

	<%
	if (listOfGames == null) {
	%>
	<h1>No results were found..</h1>
	<%
	} else {
	%>
	<%
	int quantityPerPage = 8;
	int quantityOfPages = listOfGames.size() / 8;
	if (listOfGames.size() % 8 != 0) {
		quantityOfPages++;
	}
	if (session.getAttribute("flag") == null) {
	%>
	<h1 class="text-center p-3"
		style="color: black; font-family: 'Thelma', sans-serif;">Result
		of the search..</h1>
	<%
	} else {
		session.removeAttribute("flag");
	%>
	<h1 class="text-center p-3"
		style="color: black; font-family: 'Thelma', sans-serif;">All
		games by the parameters</h1>
	<%
	}
	session.removeAttribute("flag");
	%>
	<div class="container p-2">

		<div class="row p-3">
			<%
			int start;
			if (page1 == "1") {
				start = 0;
			} else {
				start = (Integer.parseInt(page1) - 1) * 8;
			}
			int end = start + 8;
			if ((listOfGames.size() - end) < 0) {
				end = listOfGames.size();
			}
			for (int i = start; i < end; i++) {
				Game g = listOfGames.get(i);
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


							<a href="" class="btn  btn-sm ml-1"
								style="background-color: #4E4FEB; color: white;"><i
								class="fas fa-cart-plus"></i> Add Cart</a> <a href=""
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
			if (page_ != listOfGames.size()) {
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
	}
	dao1.closeConnection();
	daoGenre.closeConnection();
	daoPlatform.closeConnection();
	%>
</body>
</html>