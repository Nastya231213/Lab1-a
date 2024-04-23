<%@page import="com.entity.Genre"%>
<%@page import="com.dao.GenreDAO"%>
<%@page import="com.entity.Platform"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.PlatformDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Game</title>
<%@include file="../components/allCss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">
<style>
label {
	font-family: 'Thelma', sans-serif;
}

.custom-font {
	font-family: 'Thelma', sans-serif;
}
</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="header.jsp"%>
	<div class="container p-4">
		<div class="row">
			<div class="col-md-6 offset-md-3 ">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center" style="font-family: 'Thelma', sans-serif;">Add
							a new Game</h4>

						<form action="addGame" method="post" class="p-3"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="exampleInputEmail1">Title</label> <input type="text"
									class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="title">

							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">Price</label> <input
									type="number" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="price">

							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Release year</label> <input
									type="number" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="year">

							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Developer</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" required="required"
									name="developer">
							</div>
							<div class="col-md-13">
								<label class="form-label">Description</label>
								<textarea name="description" class="form-control" rows="3"></textarea>
							</div>
							<%
							PlatformDAO daoPl = new PlatformDAO(DBConnect.getConn());
							List<Platform> listPlatform = daoPl.getAllPlatforms();
							GenreDAO daoGenre = new GenreDAO(DBConnect.getConn());
							List<Genre> listGenre = daoGenre.getAllGenre();
							%>

							<div class="form-group">
								<label for="inputState">Platform</label> <select name="platform"
									class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp">
									<%
									for (Platform pl : listPlatform) {
									%>

									<option class="custom-font" value="<%=pl.getId()%>"><%=pl.getName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="form-group">
								<label for="inputState">Genre</label> <select name="genre"
									class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp">
									<%
									for (Genre g : listGenre) {
									%>

									<option class="custom-font" value="<%=g.getId()%>"><%=g.getName()%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="form-group">
								<label for="examleFormControlFile1">Upload Photo</label><input
									name="bimg" type="file" class="form-control-file"
									id="exampleFormControlFile1">
							</div>
							<br>
							<div align="center">
								<button type="submit" class="btn"
									style="background-color: #4E4FEB; width: 120px;">Add</button>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>