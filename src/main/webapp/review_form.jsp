<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@include file="components/allCss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=Caprasimo'
	rel='stylesheet'>
<style>
label {
	font-family: 'Thelma', sans-serif;
}

p {
	font-family: 'Thelma', sans-serif;
}
</style>
</head>
<body style="background-color: #EEEEEE;">
	<%@include file="components/header.jsp"%>
	<div class="container p-4">
		<div class="row mt-2">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center" style="font-family: 'Thelma', sans-serif">Review</h2>
						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
					</div>
				
					<form action="add_review" method="post">
					<%int g =Integer.parseInt(request.getParameter("id"));%>					
                     <input type="hidden" name="gId" value="<%=g%>"/>
						<div class="form-group col-md-12">
							<label for="exampleInputEmail1">Rating</label> <input
								class="form-control" type="text" id="exampleInputEmail1" required
							 name="rating" required>

						</div>
						<div class="form-group col-md-12">
							<label for="exampleInputEmail1">Headline</label> <input
								class="form-control" type="text" id="exampleInputEmail1"
								aria-describedby="emailHelp" name="headline" required>

						</div>
										<div class="col-md-12">
						<label class="form-label">Comment</label>
						<textarea name="comment" class="form-control" rows="3"></textarea>
					</div>

						<div class="text-center">
	
                         <br>
							<button type="submit" class="btn btn-primary"
								style="font-family: 'Thelma', sans-serif; background-color: #4E4FEB">Add the review</button>
						</div>
					</form>
					<br>
				</div>

			</div>

		</div>
	</div>
</body>
</html>