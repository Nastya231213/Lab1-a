<%@page import="com.entity.Review"%>
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
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Characteristics</title>
<%@include file="components/allCss.jsp"%>
<style>
a {
	font-family: 'Thelma';
}

a:hover {
	text-decoration: none;
}

p {
	font-family: 'Thelma';
}

.rounded {
	border-radius: 15px;
}

ul {
	list-style-type: none;
	padding: 0;
}

a {
	text-decoration: none;
	color: #318CE7;
}

a.active {
	color: #007FFF;
	font-weight: bold;
	text-decoration: underline;
}
</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="components/header.jsp"%>
	<%
	int gid = Integer.parseInt(request.getParameter("id"));
	GameDAO daoGame = new GameDAO(DBConnect.getConn());
	Game g = daoGame.getGameById(gid);
	GenreDAO genreDAO = new GenreDAO(DBConnect.getConn());
	PlatformDAO platformDAO = new PlatformDAO(DBConnect.getConn());
	%>

	<div class="container p-3 ">
		<div class="row">
			<div class="col-md-7 bg-white rounded offset-md-1">
				<div class="row">
					<a href=""><i class="fas fa-home ml-3"></i>/</a><a
						href="view_games.jsp?genre=<%=g.getGenre_id()%>"><%=genreDAO.getGenreById(g.getGenre_id()).getName()%>/</a>
					<a
						href="view_by_platform?genre=<%=g.getGenre_id()%>&&pl=<%=g.getPlatform_id()%>"><%=platformDAO.getPlatformById(g.getPlatform_id()).getName()%>/</a><a
						href=""><%=g.getDeveloper()%></a>
				</div>
				<ul class="ml-3">
					<div class="row">

						<li><a href="view_details.jsp?id=<%=g.getId()%>"
							class="<%=request.getRequestURI().endsWith("/view_details.jsp") ? "class=\"active\" ml-2" : ""%>>">All
								about the product</a></li>
						<p class="ml-2" style="color: #318CE7;">|</p>
						<li><a href="reviews.jsp?id=<%=g.getId()%>"
							class="<%=request.getRequestURI().endsWith("/reviews.jsp") ? "active" : " ml-2"%> ml-2">Reviews</a></li>
						<p class="ml-2" style="color: #318CE7;">|</p>
						<li><a href="characteristics.jspid=<%=g.getId()%>"
							class="<%=request.getRequestURI().endsWith("/characteristics.jsp") ? "active" : ""%> ml-2">Characteristics</a></li>
						<p class="ml-2" style="color: #318CE7;">
					</div>

				</ul>


				<%
				ReviewDAO reviewDAO = new ReviewDAO(DBConnect.getConn());
				List<Review> allReviews = reviewDAO.getReviewsByGameId(gid);

				String rating = reviewDAO.getRatingString(gid);
				%>
				<div class="row p-1">


					<div class="col  card ml-1 mb-2"
						style="box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);">
						<p style="font-size: 20px" class="text-center">

							<%=g.getTitle()%></p>

						<div align="left">
							<p>
								Rating:
								<c:forTokens items="<%=rating%>" delims="," var="star">
									<c:if test="${star eq 'on' }">
										<img src="image/rating-on.png" />
									</c:if>
									<c:if test="${star eq 'off'}">
										<img src="image/rating-off.png" />
									</c:if>
									<c:if test="${star eq 'half'}">
										<img src="image/rating-half.png" />
									</c:if>
								</c:forTokens>
								<a style="font-size: 14px" href="reviews.jsp?id=<%=g.getId()%>"
									class="<%=request.getRequestURI().endsWith("/reviews.jsp") ? "active" : " ml-2"%>">reviews
									<%=allReviews.size()%></a>
							</p>
							<p>
								Developer:
								<%=g.getDeveloper()%></p>
							<p>
								Published year:
								<%=g.getYear()%></p>
							<p>
								Genre:
								<%=genreDAO.getGenreById(g.getGenre_id()).getName()%></p>
							<p>
								Platform:
								<%=platformDAO.getPlatformById(g.getPlatform_id()).getName()%></p>
							<p>
								Price: <i class="fas fa-dollar-sign"></i><%=g.getPrice()%></p>

							<p>
								Description:
								<%=g.getDescription()%></p>
						</div>
					</div>


				</div>
			</div>
			<div class="col-md-3 text-center p-5 bg-white rounded">
				<div align="center">
					<img src="image/<%=g.getPhotoName()%>"
						style="width: 180px; height: 250px;">
				</div>
				<br>
				<div class="text-center p-4">
					<a href="addToCart?gid=<%=g.getId()%>" class="btn btn-primary btn-sm "
						style="width: 150px; background-color: #4E4FEB"><i
						class="fas fa-cart-plus"></i>Add Cart</a>
				</div>
				<%
				daoGame.closeConnection();
				genreDAO.closeConnection();
				platformDAO.closeConnection();
				reviewDAO.closeConnection();
				%>
			</div>
		</div>

	</div>

</body>
</html>