<%@page import="com.entity.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.entity.Game"%>
<%@page import="com.entity.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your cart</title>
<%@include file="components/allCss.jsp"%>
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

<body style="background-color: #EEEEEE;">
	<%@include file="components/header.jsp"%>
	<%User user=(User)session.getAttribute("userObj"); %>
	<div class="container p-3">
		<div class="row">
			<div class="col-md-7">
				<h3 style="font-family: 'Thelma', sans-serif;"
					class="text-center text-success">Your Selected item</h3>
				<table class="table table-bordered" style="background-color: white;">
					<thead>
						<tr>
							<th scope="col">Title</th>
							<th scope="col">Image</th>
							<th scope="col">Genre</th>
							<th scope="col">Price</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>

						<%
						Cart cart = (Cart) request.getSession().getAttribute("cart");
						HashMap items = cart.getItems();
						Iterator<Map.Entry<Game, Integer>> iterator = items.entrySet().iterator();
						double total = 0;
						while (iterator.hasNext()) {
							Map.Entry<Game, Integer> entry = iterator.next();
							Game g = entry.getKey();
							int quantity = entry.getValue();

							double price = g.getPrice() * quantity;

							total += price;
						%>
						<tr>
							<td style="font-family: 'Thelma', sans-serif;"><%=g.getTitle()%></td>

							<td><img src="image/<%=g.getPhotoName()%>"
								style="width: 70px; height: 100px;"></td>


							<td><%=quantity%></td>

							<td><i class="fas fa-dollar-sign"></i><%=price%></td>
							<td><a href="remove_from_cart?id=<%=user.getUser_id()%>" class="btn btn-sm btn-danger"
								style="background-color: #d92d53;">Remove</a></td>
						</tr>
						<%
						}
						%>
						<tr>
							<td>Total Price</td>
							<td></td>
							<td></td>
							<td><i class="fas fa-dollar-sign"></i><%=total%></td>

							<td></td>
                   <%session.setAttribute("total", total);%>

						</tr>
					</tbody>
				</table>
			</div>

			<div class="col-md-5">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center text-success"
							style="font-family: 'Thelma', sans-serif;">Your details for
							order</h3>
						<form action="order" method="post">
						<input type="hidden" name="id" value="${userObj.user_id}">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">Name</label> <input type="text" required value="<%=user.getFullName()%>"
										class="form-control" id="inputEmail4" name="fullName">
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">Email</label> <input type="email" name="email"
										class="form-control" id="inputPassword4" value="<%=user.getEmail()%>">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">Phone Number</label> <input name="phoneNum"
										type="text" class="form-control" id="inputEmail4" value="<%=user.getPhone_number()%>">
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">Address</label> <input name="address"
									 class="form-control" id="inputPassword4" value="<%=user.getAddress()%>"
										type="text">
								</div>
							</div>
							<div class="form-row">

								<div class="form-group col-md-6">
									<label for="inputPassword4">City</label> <input name="city"
										class="form-control" id="inputPassword4" type="text">
								</div>

								<div class="form-group col-md-6">
									<label for="inputEmail4">State</label> <input type="text" name="state"
										class="form-control" id="inputEmail4">
								</div>
			                </div>
			                
							<div class="form-group">
								<label>Payment Model</label> <select class="form-control">
									<option>--Select--</option>
									<option>Pay on delivery</option>
								</select>
							</div>
							<div class="text-center">
								<button class="btn btn-primary">Order Now</button>
								<a href="index.jsp" class="btn btn-success">Continue
									Shopping</a>
							</div>

						</form>
					</div>
				</div>

			</div>

		</div>
	</div>


	</div>
</body>
</html>