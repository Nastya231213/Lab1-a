<%@page import="com.entity.Platform"%>
<%@page import="com.dao.PlatformDAO"%>
<%@page import="com.entity.Genre"%>
<%@page import="com.dao.GenreDAO"%>
<%@page import="com.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<%@include file="../components/allCss.jsp"%>

<%@include file="header.jsp"%>
<style>
.btn {
	font-family: 'Thelma', sans-serif;
	color: white
}

tr {
	font-family: 'Thelma', sans-serif;
}

.shadow {
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
	border: 2px;
}
</style>

</head>
<body style="background-color: #EEEEEE;">
	<br>
	<%
	PlatformDAO platformDAO = new PlatformDAO(DBConnect.getConn());
	List<Platform> listOfPlatforms = platformDAO.getAllPlatforms();
	%><div class="col-md-6 offset-md-3 shadow" style="background-color:white;">
		<h2 style="font-family: 'Thelma', sans-serif;" class="text-center">Platforms</h2>
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
		<br>
		<table class="table table-striped " >
			<thead class="bg primary">
				<tr>

					<th scope="col">Genre ID</th>
					<th scope="col">Name</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<%
			for (Platform pl : listOfPlatforms) {
			%>
			<tr>
				<td><%=pl.getId()%></td>
				<td><%=pl.getName()%></td>
				<td><a href="delete_platform?id=<%=pl.getId()%>"
					class="btn btn-sm btn-danger" style="background-color: #d92d53;"><i
						class="fas fa-trash-alt"></i>Remove</a></td>
			</tr>
			<%
			}
			%>
		</table>
<%platformDAO.closeConnection();%>
	</div>
</body>
</html>