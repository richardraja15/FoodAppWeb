<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<style>
.table {
	margin-top: 10%;
	margin-left: 30px;
	width: 90%;
}

.rest-name {
	font-weight: 600;
	font-size: 24px;
}

.rest-number {
	font-weight: 500;
	font-size: 20px;
	margin: 2px auto;
}

.rest-address {
	font-weight: 500;
	font-size: 20px;
	margin: 2px auto;
	text-transform: capitalize;
}

.rest-city, .rest-cat {
	font-weight: 600;
	font-size: 18px;
}

</style>
<body>
<form action="RestaurantServlet" method="post">

			<div class="container">
				<div class="row">
				
			<c:forEach var="rest" items="${RESTAURANT}">
				<div class="col-md-4" >
					<div class="rest-img">
						<img src="">
					</div>
					<div class="rest-name">
					<header>${rest.restaurantName.toUpperCase()}</header>
					</div>
					<div class="rest-number">
					${rest.phoneNumber}
					</div>
					<div class="rest-address">
					${rest.address}
					</div>
					<div class="rest-city">
					${rest.city.cityName.toUpperCase()}
					</div>
					<div class="rest-cat">
					${rest.category.categoryName.toUpperCase()}
					</div>
					<button class="rest-btn" type="Submit" value="${rest.restaurantId}" name="restaurantId">View</button>
				</div>
			</c:forEach>
				</div>
			</div>
<!-- <a href="index.jsp">HOME</a> -->
</form>
</body>
</html>
<%@ include file="footer.jsp"%>