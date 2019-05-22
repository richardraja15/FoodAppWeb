<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body {
	background-image: url('blue.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

div {
	margin-left: 40%;
	margin-top: 10%;
}
</style>

<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap.css">
</head>
<body>
	<form action="LoginServlet" method="post">
		<div>
			<table>
				<tr>
					<td colspan="3" align="center"><h1>LOGIN</h1> <br> <br></td>
				</tr>
				<tr>
					<td align="center"><label class="col-form-label-lg">USERNAME</label></td>
					<td align="center">:</td>
					<td align="center"><input type="text" name="userId"
						class="form-control" required="required"></td>
				</tr>
				<tr>
					<td align="center"><label class="col-form-label-lg">PASSWORD</label></td>
					<td align="center">:</td>
					<td align="center"><input type="password" name="password"
						class="form-control" required="required"></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><label style="color: red">${ERROR_MSG}</label></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><button type="SUBMIT"
							class="btn btn-success">SUBMIT</button></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><br>
						
							<a href="index.jsp"><button
									type="button" class="btn btn-success">Home</button></a>
						</td>
				</tr>
				
			</table>
		</div>
	</form>

</body>
</html>
