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

.amount {
	position: absolute;
	bottom: 80px;
	left: 50%
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
					for(var key in orderedMenu){
					if(orderedMenu.hasOwnProperty(key)){
						total+=Number(orderedMenu[key].totalPrice);
						$("#orderedElement").append("<div class='order-name-rest' >restaurantName : "+orderedMenu[key].restaurantName+"</div>");
						$("#orderedElement").append("<div class='order-name'>menuName: "+orderedMenu[key].menuName+"</div>");
						$("#orderedElement").append("<div class='order-quantity'>Quantity : "+orderedMenu[key].quantity+"</div>");
						$("#orderedElement").append("<div class='order-total-price'>Price : "+orderedMenu[key].totalPrice+"</div>");
						$("#menuitems").hide();
						$("#viewItems").show();
						}
					}
						$("#finalAmount").show();
						$(".amount").show();
						document.getElementById("finalAmount").innerHTML = "Total Price : Rs." + total;
						document.getElementById("restaurentCharges").innerHTML = "Restaurant Charges : Rs." + (total * 0.06).toFixed(2);
						document.getElementById("totalAmount").innerHTML = "Total Charge: Rs." + (total + total * 0.06 + 25).toFixed(2);
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

				<!-- <input type="number" name="quantity"> -->
				<span class="cart-add">Cart
					<button type="submit" name="menuName" value="${menu.menuId }"
						class="addcart">Add Cart</button>
				</span>
			</div>
		</c:forEach>
	</div>
	<button class="btn-center btn-cart" type="submit" id="orderbtn">Order</button>
</div>
<div id="viewItems">
	<div class="container">
		<div class="row">
			<div id="orderedElement"></div>
			<br>
			<div class="amount">
				<div id="finalAmount"></div>
				<div id="restaurentCharges"></div>
				<div>Delivery Charge: Rs.25</div>
				<div id="totalAmount"></div>
				<form action="UserLogin.jsp" method="post">
					<br><button id="orderBtn">Confirm Order</button>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>