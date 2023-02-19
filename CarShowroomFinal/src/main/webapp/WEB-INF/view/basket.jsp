<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-image: url("https://wallpaperaccess.com/full/2749988.jpg")
}

div.gallery {
	margin: 5px;
	border: 1px solid #ccc;
	float: left;
	width: 250px;
}

div.gallery:hover {
	border: 1px solid #777;
}

div.gallery img {
	width: 100%;
	height: 140px;
}

div.desc {
	padding: 15px;
	text-align: center;
}
</style>

<title>Your basket</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>

<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Car showroom False</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li><a
					href="http://localhost:8084/spring-security-custom-user-registration-demo/car/list">Cars</a></li>
				<li><a
					href="http://localhost:8084/spring-security-custom-user-registration-demo/brand/list">Brands</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">

				<li><a> <form:form
							action="${pageContext.request.contextPath}/logout" method="POST">
							<input type="submit" value="Logout" class="btn btn-info" />
						</form:form>
				</a></li>
			</ul>
		</div>
	</nav>

	<h2 style="color: white">Total price: ${totalPrice}$</h2>
	<form:form action="deleteUserCars" modelAttribute="userCars" method="GET">
		<c:forEach var="tempCar" items="${userCars}">
			<div class="gallery">
				<img src="${tempCar.image}" alt="${tempCar.model}" width="600"
					height="400" />

				<div class="desc" style="background-color: grey; color: white">
					Brand ${tempCar.brandId.brandName}<br> Model ${tempCar.model}<br>Price
					${tempCar.price}$<br>
				</div>
			</div>
		</c:forEach>
		<br>
		<c:if test="${userCars.size() != 0}">
			<button type="submit" class="btn btn-info">Buy</button>
		</c:if>
		<br>
	</form:form>
	<c:if test="${userCars.size() != 0}">
		<form:form action="removeFromBasket" method="GET">
			<button type="submit" class="btn btn-warning">Remove</button>
		</form:form>
	</c:if>
</body>