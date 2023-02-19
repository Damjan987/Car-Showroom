<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Insert car</title>
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
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><a>  <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" class="btn btn-info" />
		</form:form> </a> </li>
      </ul>
    </div>
  </nav>
  <div style="width:20%; margin-left:50px">
  	<div id="container">
  		<h2>Create car</h2>
      <br>
      
      <form:form action="saveCar1"  modelAttribute="car" method="POST"> 
        <form:hidden path="id" />
        <div class="form-group">
          <label>Car model:</label>
          <br>
          <form:input path="model"/> 
          
        </div>
        <div class="form-group">
          <label>Brand name:</label>
          <br>
          <select name="brand" path="brandId">
			<c:forEach var="tempBrand" items="${brands}">
				<option value="${tempBrand.id}"> ${tempBrand.brandName} </option>
			</c:forEach>
		  </select>
        </div>
        
        <div class="form-group">
          <label>Price:</label>
          <br>
          <form:input path="price"/>
        </div>
        <div class="form-group">
          <label>Image:</label>
          <br>
          <input type="file" name="file"/>
          
        </div>
        <button type="submit" class="btn btn-dark">Save</button>
      </form:form>
      <form action="http://localhost:8084/spring-security-custom-user-registration-demo/car/list">
  	  
  	  </form>
    </div>
  </div>
</body> 
</html>
