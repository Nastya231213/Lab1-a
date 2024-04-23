<%@page import="com.entity.Order"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.OrderDAO"%>
<%@page import="com.entity.User"%>
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
td {
	font-family: 'Thelma', sans-serif;
}

th {
	font-family: 'Thelma', sans-serif;
}

label {
	font-family: 'Thelma', sans-serif;
}
</style>
</head>
<body>
	<%@include file="header.jsp"%>
	<%
	int orderId = Integer.parseInt(request.getParameter("id"));
	OrderDAO orderDAO = new OrderDAO(DBConnect.getConn());
	Order order = orderDAO.getOrderById(orderId);
	%>

	<div class="col-md-6 offset-md-3">
		<div class="card">
			<div class="card-body">
				<h3 class="text-center text-success"
					style="font-family: 'Thelma', sans-serif;">Edit order</h3>


				<form action="edit_order" method="post">
					<input type="hidden" name="id" value="<%=orderId%>">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Name</label> <input type="text" required
								value="<%=order.getFullName()%>" class="form-control"
								id="inputEmail4" name="fullName">
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail4">Email</label> <input type="email"
								required value="<%=order.getEmail()%>" class="form-control"
								id="inputEmail4" name="email">
						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Phone Number</label> <input
								name="phoneNum" type="text" class="form-control"
								id="inputEmail4" value="<%=order.getPhone()%>">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Address</label> <input name="address"
								class="form-control" id="inputPassword4"
								value="<%=order.getAddress()%>" type="text">
						</div>
					</div>
					<div class="form-row">

						<div class="form-group col-md-6">
							<label for="inputPassword4">City</label> <input name="city"
								class="form-control" value="<%=order.getCity()%>"
								id="inputPassword4" type="text">
						</div>

						<div class="form-group col-md-6">
							<label for="inputEmail4">State</label> <input type="text"
								name="state" class="form-control" id="inputEmail4"
								value="<%=order.getState()%>">
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6">
							<label for="inputEmail4">Status</label> <input type="text"
								name="status" class="form-control" id="inputEmail4"
								value="<%=order.getStatus()%>">
						</div>
					</div>



					<div class="text-center">
						<button type="submit" class="btn btn-primary">Edit</button>

					</div>
<%orderDAO.closeConnection(); %>
				</form>
			</div>
		</div>
	</div>

</body>
</html>