<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title> List brands </title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
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
	        <li><a href="${pageContext.request.contextPath}/car/list">Cars</a></li>
	        <li><a href="${pageContext.request.contextPath}/brand/list">Brands</a></li>
	        
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
	
	<div style="margin-left: 30px;">
	<div id="wrapper">	
		<div id="header">
			<h2> Brands </h2>
		</div>
		
		<div id="container">
			
			<div id="content">
				
				<security:authorize access="hasRole('ADMIN')">
	     			<input type="button" value="Add brand" onclick="window.location.href='brandForm'; return false;" class="btn btn-info" />
				</security:authorize>
				<br>
				<br>
				<table>
					<tr>
						<th> Brand name </th> <th></th>
						<security:authorize access="hasRole('ADMIN')">
						
						</security:authorize>
					</tr>
					<c:forEach var="tempBrand" items="${brands}">
						<c:url var="updateLink" value="/brand/showFormForUpdate">
							<c:param name="brandId" value="${tempBrand.id}" />
						</c:url>
						<c:url var="deleteLink" value="/brand/delete">
							<c:param name="brandId" value="${tempBrand.id}" />
						</c:url>
						<tr> 
							<td> ${tempBrand.brandName} </td>
							<security:authorize access="hasRole('ADMIN')">
							<td><a href="${updateLink}"> Update </a></td>
							<td style="width: 10px;">  </td>
							<td> <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want delete this brand'))) return false")> Delete </a> </td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</div>
	</div>
</body>

</html>