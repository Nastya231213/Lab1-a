<link href='https://fonts.googleapis.com/css?family=Caprasimo' rel='stylesheet'>
<link href="https://fonts.cdnfonts.com/css/thelma-2" rel="stylesheet">

<style>

.navbar{
font-family: 'Thelma', sans-serif;}
.btn{
font-family: 'Thelma', sans-serif;
background-color:#068FFF ; color:white}

</style>
<div class="container-fluid"
	style="height: 10px; background-color: black"></div>
<div class="container-fluid p-3">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="row">
		<div class="col-md-9 text-success">
			<h3 style="font-family: 'Thelma'; color:#4E4FEB">
				<i class="fas fa-dice-d20"></i>Games Collection Shop
			</h3>
		</div>

		<div class="col-md-3" align="right">
			<a href="login" class="btn btn-success" style="background-color:#4E4FEB ; color:white"><i class="fas fa-user-circle" ></i>Admin</a> <a href="../admin_logout"
				class="btn btn-primary ">Logout</a>
		</div>



	</div>


</div>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#000000;">
 
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
     <a class="nav-link" href="admin.jsp"><i class="fas fa-home"></i>Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">

      <li class="nav-item active">
        <a class="nav-link" href="genre_edit.jsp"><i class="fas fa-tape"></i>Genres</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="platform_edit.jsp"><i class="fas fa-window-restore"></i>Platforms</a>
      </li>

 
    </ul>
  </div>
</nav>
