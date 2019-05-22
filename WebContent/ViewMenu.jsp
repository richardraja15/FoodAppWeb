<%@ include file="header.jsp"%>

<style>
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
    margin-left: 73%;
    box-shadow: 0px 0px 4px 2px rgba( 0, 0, 0, 0.2 );
    height: 180px;
    font-size: 17px;
    font-weight: 500;
}

#orderBtn{
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
    font-weight: 600;
}

.order-quantity {
	font-size: 20px;
    font-weight: 600;
}

.order-total-price {
	font-size: 20px;
    font-weight: 600;
}

#form-btn {
	width: 128px;
	margin: 0 auto;
}

.btn-cart {
    padding: 4px;
    padding-left: 20px;
    padding-right: 20px;
    width: 80px;
    background: none;
    border: none;
    border: 2px solid;
    transition: all 0.3s ease;
}

.btn-cart:hover {
	background: black;
	color: white;
}

</style>

<script src="js/jquery/jquery-2.2.4.min.js"></script>
<script>
	$(document).ready(function() {
		$("#viewItems").hide();
		$("#finalAmount").hide();
		$(".amount").hide();
		var arr = [];
		var x="${menuId}";
		var a = [];
		var cart = new Object();
		for (var i = 0; i < x.length; i++) {
			if (Number(x[i])) {
				a.push(x[i])
			}
		}
		for(var i = 0; i < a.length; i++) {
			var b = a[i].toString();
			cart[b] = 0;
		}
		var tempCart = {...cart};
		$(".quantity").change(function(e) {
			tempCart[e.target.name] = Number(e.target.value);
		})
		/* var orderedList=[];
		var cartList=new Object(); */
		$(".addcart").click(function(e) {
			cart[e.target.value] += tempCart[e.target.value];	
			var tempKeys = Object.keys(cart);
			var tempValues = Object.values(cart);
			arr = [];
			tempKeys.forEach(function(t,i) {
				var obj = {
					menuId: tempKeys[i],
					quantity: tempValues[i]
				};
				arr.push(obj);
			});
		});
		
		$('#orderbtn').click(function(){
			$.ajax({
				url:'AddCartServlet',
				type:'post',
				data:{CartList:JSON.stringify(arr)},
				success:function(data){
					var orderedMenu=JSON.parse(data);
					var total = 0;
					$(".heading-text").append("<h1>" + orderedMenu[0].restaurantName.toUpperCase() + "</h1><hr>");
					for(var key in orderedMenu){
					if(orderedMenu.hasOwnProperty(key)){
						total+=Number(orderedMenu[key].totalPrice);
						$("#orderedElement").append("<div class='order-name'>Item: "+orderedMenu[key].menuName+"</div>");
						$("#orderedElement").append("<div class='order-quantity'>Quantity: "+orderedMenu[key].quantity+"</div>");
						$("#orderedElement").append("<div class='order-total-price'>Price: "+orderedMenu[key].totalPrice+"</div><hr>");
						$("#menuitems").hide();
						$("#viewItems").show();
						}
					}
						$("#finalAmount").show();
						$(".amount").show();
						document.getElementById("finalAmount").innerHTML = "Total Price: Rs." + total;
						document.getElementById("restaurentCharges").innerHTML = "Restaurant Charges : Rs." + (total * 0.06).toFixed(2);
						document.getElementById("totalAmount").innerHTML = "Total Amount: Rs." + (total + total * 0.06 + 25).toFixed(2);
						$("#orderBtn").val((total + total * 0.06 + 25).toFixed(2));
				}
				
				
			});
			$("#orderBtn").click(function() {
				console.log($("#orderBtn").val());
			})
		});
			});
</script>


<!-- <form action="#" method="post"> -->
<div class="container" id="menuitems">
	<div class="row">

		<c:forEach var="menu" items="${MENUNAME}">
			<div class="cont col-md-4">
				<img class="cont-img img-display" src="${menu.image}" alt="">
				<span class="cont-heading"> ${menu.name} </span> <br> <span
					class="cont-price"> Rs.${menu.price} </span> <br> <select
					class="select quantity" name="${menu.menuId}">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
				</select>
				<br>
				<!-- <input type="number" name="quantity"> -->
				<div class="cart-add">
					<button type="submit" name="menuName" value="${menu.menuId }"
						class="addcart">Add Cart</button>
				</div>
			</div>
		</c:forEach>
	</div>
	<button class="btn-center btn-cart" type="submit" id="orderbtn">Order</button>
</div>
<div id="viewItems">
	<div class="container">
		<div>
			<div class="heading-text"></div>
			<div id="orderedElement"></div>
			<br>
			<div class="amount">
				<div id="finalAmount" name="finalAmount"></div>
				<div id="restaurentCharges"></div>
				<div>Delivery Charge: Rs.25</div>
				<div id="totalAmount"></div>
				<form id="form-btn" action="UserLogin.jsp" method="post">
					<br><button id="orderBtn">Confirm Order</button>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>