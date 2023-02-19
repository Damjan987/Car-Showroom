<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-image:url("https://wallpaperaccess.com/full/2749988.jpg")
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
	<title> List customers </title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
</head>

<body>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Car showroom False</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/car/list/">Cars</a></li>
        <li><a href="${pageContext.request.contextPath}/brand/list">Brands</a></li>
        
        <li><a href="${pageContext.request.contextPath}/car/basket">
        	<button style="font-size:24px">
        	<i class="fa fa-shopping-cart"></i></button></a></li>
      </ul>
        
      <ul class="nav navbar-nav navbar-right">
      	<li><a>  <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" class="btn btn-info" value="Logout" />
		</form:form> </a> </li>
      </ul>
    </div>
  </nav>
	
	 <img src="CarShowroom/src/main/resources/static/img/audia3.jpg" alt="" width="600" height="400" />  
	
	<security:authorize access="hasRole('ADMIN')">
		<div>
			<input type="button" value="Add car" onclick="window.location.href='carForm'; return false;" class="btn btn-info" />
			
			<a href="${pageContext.request.contextPath}/car/deleteSelectedCars"><button class="btn btn-danger">
				Delete selected cars</button></a> 
				
			<a href="${pageContext.request.contextPath}/car/unselect"><button class="btn btn-warning">
				Unselect selected cars</button></a> 
		</div>
		<br>
	</security:authorize>
	
   	<form:form action="search" method="GET">
        <p style="color:white; size:25px"> Search cars:</p> <input type="text" name="theSearchName" /> 
        <input style="color:white" type="submit" value="Search" class="btn btn-success" />
   	</form:form>
	<br>
	<form:form action="filter" method="GET">
	
		<label for="car" style="color:white; size:20px">Filter by price</label>
		<select name="minPrice" id="minPrice">
			<option value="4000.0">4000</option>
			<option value="5000.0">5000</option>
			<option value="6000.0">6000</option>
			<option value="7000.0">7000</option>
			<option value="8000.0">8000</option>
			<option value="8500.0">8500</option>
		</select>
		
		<select name="maxPrice" id="maxPrice">
			<option value="8500.0">8500</option>
			<option value="9000.0">9000</option>
			<option value="10000.0">10000</option>
			<option value="11000.0">11000</option>
			<option value="12000.0">12000</option>
			<option value="13000.0">13000</option>
			<option value="14000.0">14000</option>
			<option value="15000.0">15000</option>
			<option value="20000.0">20000</option>
			<option value="25000.0">25000</option>
			<option value="30000.0">30000</option>
			<option value="35000.0">35000</option>
		</select>
		<input type="submit" value="Search" class="btn btn-success" />	
	</form:form>
	<br>
<!-- 	<img src="audir8.jpg" width="600" height="400" >  -->
	<c:set var="counter" scope="session" value="${0}" />
	<c:forEach var="tempCar" items="${cars}">
		
		<c:if test="${tempCar.userId==null}">
			<div class="gallery">
				<img src="${tempCar.image}" alt="${tempCar.model}" width="600" height="400" />
				<c:url var="updateLink" value="/car/showFormForUpdate">
					<c:param name="carId" value="${tempCar.id}" />
				</c:url>
				<c:url var="deleteLink" value="/car/delete">
					<c:param name="carId" value="${tempCar.id}" />
				</c:url>			
								
				<div class="desc" style="background-color:grey; color:white">Brand: ${tempCar.brandId.brandName}<br>
					Model: ${tempCar.model}<br>Price: ${tempCar.price}$<br>
					<a name="uid" value="${user.id}" href="${deleteLink}" 
					onclick="if (!(confirm('Are you sure you want to add this car'))) return false"><button>Add to cart</button></a>
								
					<security:authorize access="hasRole('ADMIN')">
						<a href="${updateLink}"><button>Update</button></a>
						<form:form action="addSelectedCar" modelAttribute="carId" method="POST" >
							<label for="${tempCar.id}"></label>	 
	
							<c:if test="${carsToDelete.size() == 0}">
								<form:checkbox path="id" value="${tempCar.id}" onchange="this.form.submit()" name="${tempCar.id}" />
							</c:if>
							<c:if test="${carsToDelete.size() > 0}">
								<c:if test="${carIndexes.get(counter) == 0}">
									<form:checkbox path="id" value="${tempCar.id}" onchange="this.form.submit()" name="${tempCar.id}" />
								</c:if>
								<c:if test="${carIndexes.get(counter) == 1}">
									<form:checkbox path="id" value="${tempCar.id}" onchange="this.form.submit()" 
									name="${tempCar.id}" checked="checked"/>
								</c:if>
							</c:if>
						<c:set var="counter" scope="session" value="${counter + 1}" />
						</form:form>							  					
					</security:authorize>
				</div>
			</c:if>
		</div>
	</c:forEach>	
	 
</body>
</html>
