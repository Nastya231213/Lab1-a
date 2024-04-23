<%@page import="com.entity.User"%>
<%@page import="com.dao.UserDAO"%>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=Caprasimo'
	rel='stylesheet'>

<meta charset="ISO-8859-1">
<title>View Details</title>
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
	UserDAO userDAO = new UserDAO(DBConnect.getConn());
	PlatformDAO platformDAO = new PlatformDAO(DBConnect.getConn());
	%>

	<div class="container p-3">
		<div class="row">
			<div class="col-md-12 border bg-white rounded">
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
						<li><a href="characteristics.jsp?id=<%=g.getId()%>"
							class="<%=request.getRequestURI().endsWith("/characteristics.jsp") ? "active" : ""%> ml-2">Characteristics</a></li>
						<p class="ml-2" style="color: #318CE7;">
					</div>

				</ul>
				<div class="text-center">
					<a href="review_form.jsp?id=<%=g.getId()%>"
						class="btn btn-primary btn-sm"
						style="width: 200px; background-color: #4E4FEB"><i
						class="fas fa-comment"></i>Add Review</a>
				</div><br><br>
				<h2 style="font-family: 'Thelma'" class="text-center">All the reviews to "<%=g.getTitle()%>"</h2>
				<hr>
					<table>
						<%
						ReviewDAO daoRev = new ReviewDAO(DBConnect.getConn());
						List<Review> allReviews = daoRev.getReviewsByGameId(gid);
						%>
						<%
						for (Review rev : allReviews) {
							
						%>
						<tr>
							<td><c:forTokens items="<%=daoRev.getRatingString(rev.getRating())%>"
									delims="," var="star">
									<c:if test="${star eq 'on' }">
										<img src="image/rating-on.png" />
									</c:if>
									<c:if test="${star eq 'off'}">
										<img src="image/rating-off.png" />
									</c:if>
									<c:if test="${star eq 'half'}">
										<img src="image/rating-half.png" />
									</c:if>
								</c:forTokens></td>
						</tr>
						<%
						User u = userDAO.getUserById(rev.getUser_id());
						%>
						<tr>
							<td><p>
									by
									<%=u.getFullName()%></p></td>

						</tr>
						<tr><td><p><%=rev.getComment()%></p></td></tr>
						<%
						}
						%>
					</table>

			</div>
		</div>

	</div>
<% daoGame.closeConnection();
genreDAO.closeConnection();
platformDAO.closeConnection();
userDAO.closeConnection();
daoRev.closeConnection();
%>
</body>
</html>