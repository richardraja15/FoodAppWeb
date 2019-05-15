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
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var x="${menuId}";
		console.log(x)
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
		var orderedList=[];
		var cartList=new Object();
		$(".addcart").click(function(e) {
			cart[e.target.value] += tempCart[e.target.value];
			console.log(cart);	
			cartList['menuid']=tempCart[e.target.name];
			cartList['quantity']=cart[e.target.value];
		});
		
		orderedList.push(cartList);
		console.log(orderedList);
	});
</script>


<!-- <form action="#" method="post"> -->
<div class="container">
	<div class="row">

		<c:forEach var="menu" items="${MENUNAME}">
			<div class="cont col-md-4">
				<img class="cont-img" src="img/bg-img/bg1.jpg" alt=""> <span
					class="cont-heading"> ${menu.name} </span> <br> <span
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
		<button class="btn-cart" type="submit">Order</button>
	</div>
</div>

<!-- <a href="index.jsp">HOME</a> -->
<!-- </form> -->
<%@ include file="footer.jsp"%>