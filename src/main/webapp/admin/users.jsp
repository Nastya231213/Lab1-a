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
</style>

</head>
<body>
<br>
	<h2 style="font-family: 'Thelma', sans-serif;" class="text-center">Users</h2>
		<c:if test="${not empty succMsg}">
		<p class="text-center "
			style="font-family: 'Thelma', sans-serif; color: #03C988">${succMsg}</p>
		<c:remove var="succMsg" scope="session" />

	</c:if>
	<c:if test="${not empty errorMsg}">
		<p class="text-center"
			style="font-family: 'Thelma', sans-serif; color: #4E4FEB">${errorMsg}</p>
		<c:remove var="errorMsg" scope="session" />

	</c:if>a
	<br><br>
	<table class="table table-striped">
		<thead class="bg primary">
			<tr>
							<th scope="col">Username</th>
			
				<th scope="col">Username</th>
				<th scope="col">Email</th>
				<th scope="col">Phone Number</th>
				<th scope="col">Address</th>
				<th scope="col">Registration Date</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<%
		UserDAO dao = new UserDAO(DBConnect.getConn());
		List<User> listUsers = dao.getAllUsers();
		for (User user : listUsers) {
		%>
		<tr><td><%=user.getUser_id() %></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getEmail()%></td>
			<td><%=user.getPhone_number()%></td>
			<td><%=user.getAddress()%></td>
			<td><%=user.getRegistration_date()%></td>
			<td class="row"><a href="edit_user.jsp?id=<%=user.getUser_id()%>"
				class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i>Edit</a>  <a href="user_remove?id=<%=user.getUser_id()%>" class="btn btn-sm btn-danger"
				style="background-color: #d92d53;"><i class="fas fa-trash-alt"></i>Remove</a>
			</td>
		</tr>
		<%
		}
		%>
	</table>

<%dao.closeConnection();%>
</body>
</html>