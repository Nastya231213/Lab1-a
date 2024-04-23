>
<%@page import="com.entity.Game"%>
<%@page import="com.dao.GameDAO"%>
<%@page import="com.entity.OrderItem"%>
<%@page import="com.dao.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/components/allCss.jsp"%>

<%@include file="/components/header.jsp"%>
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
	GenreDAO genreDAO = new GenreDAO(DBConnect.getConn());
	List<Genre> listOfGenres = genreDAO.getAllGenre();
	%><div class="col-md-6 offset-md-3 shadow"
		style="background-color: white;">
		<h2 style="font-family: 'Thelma', sans-serif;" class="text-center">Details of the order</h2>
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
		<%
		int orderId = Integer.parseInt(request.getParameter("id"));
		OrderDAO orderDAO = new OrderDAO(DBConnect.getConn());
		List<OrderItem> orderList = orderDAO.getOrderItems(orderId);
		GameDAO gameDAO = new GameDAO(DBConnect.getConn());
		%>
		<table class="table table-striped">
			<thead class="bg primary">
				<tr>

                    <th scope="col">Image</th>
					<th scope="col">Title</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
				</tr>
			</thead>
			<%
			for (OrderItem orderItem : orderList) {
				Game g = gameDAO.getGameById(orderItem.getItem_id());
			%>
			<tr>
				<td><img src="image/<%=g.getPhotoName()%>"
					style="width: 70px; height: 100px;"></td>
				<td><%=g.getTitle()%></td>
				<td><%=g.getPrice()%></td>
				<td><%=orderItem.getQuantity()%></td>
			</tr>
			<%
			}
			%>
		</table>
       <%
       gameDAO.closeConnection();
       orderDAO.closeConnection();
       genreDAO.closeConnection();
       %>
	</div>
</body>
</html>