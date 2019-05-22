<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div>
	<div>Account Holder Name: </div><%out.print( request.getParameter("acc_holder_name")); %>
	</div>
		
	<div>
	<div>Transaction Id: </div><%out.print( request.getParameter("trn_id")); %>
	</div>
	<div>
	<div>Transaction Status: </div><%out.print( request.getParameter("trn_status")); %>
	</div>
</body>
</html>