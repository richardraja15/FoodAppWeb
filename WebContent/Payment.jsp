<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: Roboto;
}

.heading-text {
	width: 500px;
	margin: 0 auto;
	text-align: center;
}

.cont-heading {
	font-size: 27px;
	text-transform: capitalize;
}

.cont-price {
	font-size: 18px;
	font-weight: 100;
}

.cart-add {
	margin-top: 50px;
}

.quantity {
	width: 60.94px !important;
}

.cont {
	margin: 10px auto;
}

.icon {
	font-size: 20px;
}

.cart {
	padding: 0 8px;
}

.order-container {
	background-color: red;
}

.heading-text {
	width: 500px;
	margin: 0 auto;
	text-align: center;
}

.amount {
	padding: 10px;
	width: 300px;
	margin: 0;
	margin-left: 73%; box-shadow : 0px 0px 4px 2px rgba( 0, 0, 0, 0.2);
	height: 180px;
	font-size: 17px;
	font-weight: 400;
	box-shadow: 0px 0px 4px 2px rgba(0, 0, 0, 0.2);
}

#orderBtn {
	background: none;
	border: none;
	border: 2px solid;
	padding: 4px;
	transition: all 0.3s ease;
}

#orderBtn:hover {
	background: black;
	color: white;
}

.img-display {
	width: 350px;
	height: 350px;
	object-fit: contain;
}

.btn-center {
	width: 60px;
	margin-left: 47.2%;
}

.order-name {
	text-transform: capitalize;
	font-size: 20px;
	font-weight: 400;
}

.order-quantity {
	font-size: 20px;
	font-weight: 400;
}

.order-total-price {
	font-size: 20px;
	font-weight: 400;
}
</style>
</head>
<body>
	<form action="http://localhost:8081/PaymentService/ExternalServlet"
		method="post">
		<div id="viewItems">
			<div class="container">
				<div>
					<h1 class="heading-text"></h1>
					<hr>
					<c:forEach var="food" items="${FOODLIST}">
						<div class="heading-rest" style="display: none;">${food.restaurantName}</div>
						<div id="orderedElement">
							<div class='order-name'>Item:${food.menuName}</div>
							<div class='order-quantity'>Quantity:${food.quantity}</div>
							<div class='order-total-price'>Price:${food.totalPrice}</div>
							<hr>
						</div>
						<br>
					</c:forEach>
					<c:forEach var="food1" items="${FOODCHARGES}">
						<div class="amount">
							<div id="finalAmount" name="finalAmount">Total Price:
								${food1.totalAmount}</div>
							<div id="restaurentCharges">Restaurant
								Charges:${food1.restaurantCharges}</div>
							<div>Delivery Charge: Rs.25</div>
							<div id="totalAmount">Total Amount:${food1.finalAmount}</div>
							<br> <input type="hidden" name="paymentAmount"
								id="paymentAmount" value="${food1.finalAmount}"> <input type="hidden"
								name="retUrl" id="retUrl"
								value="http://localhost:8081/Admission/Success.jsp">

							<button id="orderBtn" type="submit">Confirm Payment</button>


						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</form>
	<script src="js/jquery/jquery-2.2.4.min.js"></script>
	<script>
		var restName = document.querySelector(".heading-rest").innerText;
		$("<div>" + restName.toUpperCase() + "</div>")
				.appendTo(".heading-text");
	</script>
</body>
</html>