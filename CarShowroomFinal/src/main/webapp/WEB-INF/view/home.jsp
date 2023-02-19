<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>	
	

<style>

body {
	background-color: lightgrey;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}

input {
	color: white;
	background-color: black;
}

.povecaj {
	font-size: 50px;
	text-align: center;	
}

</style>

	<title> AUTO SALON </title>

</head>

<body>
	
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Car showroom False</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="http://localhost:8084/spring-security-custom-user-registration-demo/car/list">Cars</a></li>
        <li><a href="http://localhost:8084/spring-security-custom-user-registration-demo/brand/list">Brands</a></li>
        
		<li><a href="${pageContext.request.contextPath}/car/basket">	
        	<button style="font-size:24px">
        	<i class="fa fa-shopping-cart"></i></button></a></li>
      </ul>
			
      <ul class="nav navbar-nav navbar-right">
      	
      	<li><a>  <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" class="btn btn-info" />
	</form:form> </a> </li>
             
      </ul>
    </div>
  </nav>
  	
		<h1 class="povecaj"> Car showroom False </h1>
	<hr><hr>
	<p>
		<h2 style="text-align:center" > Welcome <security:authentication property="principal.username" /> </h2>
		<br><br>
	<hr><hr>
		<br><br>
			First name: ${user.firstName} <br>
			Last name: ${user.lastName} <br>
		 	Email: ${user.email} <br>
	</p>
	<hr>	
	
</body>
</html>
