<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
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
</style>
<body>
<form action="ViewController" method="post">

	<table class="table table-striped" id="mytab1">
		<thead class="thead-dark">
			<tr>
				<th>Item</th>
				<th>Price</th>
				<th></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="menu" items="${MENUNAME}">
				<tr >
				
					<td>
					<header>${menu.name}</header>
					</td>
					<td>${menu.price}</td>
					<td><button type="Submit">ORDER</button></td>
					
				</tr>
			</c:forEach>

		</tbody>

	</table>
<a href="index.jsp">HOME</a>
</form>
</body>
</html>