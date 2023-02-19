<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Insert brand</title>
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
        <li><a href="${pageContext.request.contextPath}/register/showRegistrationForm"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="${pageContext.request.contextPath}/showMyLoginPage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </nav>
  <div style="width:20%; margin-left:50px">
      <div id="container">
          <h2>Create brand</h2>
      <br>
      <form:form action="saveBrand" modelAttribute="brand" method="POST">
        <form:hidden path="id" />
        <div class="form-group">
          <label>Brand name:</label>
          <form:input path="brandName"/>
        </div>
        <button type="submit" class="btn btn-dark">Save</button>
      </form:form>
      
    </div>
  </div>
</body>
</html>
