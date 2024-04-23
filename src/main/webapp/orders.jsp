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
<%@include file="components/allCss.jsp"%>

<%@include file="components/header.jsp"%>
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
	<h2 style="font-family: 'Thelma', sans-serif;" class="text-center">My Orders</h2>
	<table class="table table-striped p-2">
		<thead class="bg primary">
			<tr>
				<th scope="col" class="text-center">Order ID</th>

				<th scope="col" class="text-center">price</th>
				<th scope="col" class="text-center">Address</th>
				<th scope="col" class="text-center">Date</th>
				<th scope="col" class="text-center">Action</th>
			</tr>
		</thead>
		<%
		int user_id=Integer.parseInt(request.getParameter("id"));
		OrderDAO dao_order=new OrderDAO(DBConnect.getConn());
		List<Order> allOrders=dao_order.getAllOrderByUserid(user_id);
		for(Order order:allOrders){
		%>
		<tr>
			<td class="text-center"><%=order.getOrderId()%></td>
			<td class="text-center"><i class="fas fa-dollar-sign"></i><%=order.getPrice()%></td>

			<td class="text-center"><%=order.getAddress()%></td>
			<td class="text-center"><%=order.getDate()%></td>
			<td class="text-center"  ><a href="items_order.jsp?id=<%=order.getOrderId()%>"
				class="btn btn-sm btn-primary text-center"><i class="fas fa-edit"></i>Details of the order</a>  
			</td>
		</tr>
		<%
		}
		%>
	</table>

<%dao_order.closeConnection(); %>
</body>
</html>