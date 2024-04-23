<%@page import="com.entity.Order"%>
<%@page import="com.dao.OrderDAO"%>
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
	<h2 style="font-family: 'Thelma', sans-serif;" class="text-center">Orders</h2>
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

	<
	<br>
	<br>
	<table class="table table-striped">
		<thead class="bg primary">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Full name</th>
				<th scope="col">Address</th>
				<th scope="col">City</th>
				<th scope="col">State</th>
				<th scope="col">User ID</th>
                <th scope="col">State</th>
				<th scope="col">Price</th>
				<th scope="col">Date</th>
				<th scope="col">Action</th>
			</tr>
		</thead>
		<%
		OrderDAO orderDAO = new OrderDAO(DBConnect.getConn());
		List<Order> listOrders = orderDAO.getAllOrders();

		for (Order order : listOrders) {
		%>
		<tr>
			<td><%=order.getOrderId()%></td>
			<td><%=order.getFullName()%></td>
			<td><%=order.getAddress()%></td>
			<td><%=order.getCity()%></td>
			<td><%=order.getState()%></td>
			<td><%=order.getUserId()%></td>
			<td><%=order.getPrice()%></td>
			
			<td><%=order.getPrice()%></td>
			<td><%=order.getDate()%></td>
			<td class="row"><a
				href="edit_order.jsp?id=<%=order.getOrderId()%>"
				class="btn btn-sm btn-primary mr-2"><i class="fas fa-edit"></i>Edit</a>
				<a href="remove_order?id=<%=order.getOrderId()%>"
				class="btn btn-sm btn-danger" style="background-color: #d92d53;"><i
					class="fas fa-trash-alt"></i>Remove</a></td>
		</tr>
		<%
		}
		%>
	</table>
<%orderDAO.closeConnection();%>

</body>
</html>