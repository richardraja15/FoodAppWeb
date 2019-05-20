               <!-- ##### Footer Area Start ##### -->

    <!-- ##### All Javascript Files ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
    <script>
		fetch("RestaurantServlet")
		.then(res => res.json())
		.then(data => {
			data.forEach(d => {
				//$("#drop").append("<li><a>" + d.restaurantName + "</a></li>");
				$("#drop").append("<form action='RestaurantServlet' method='post'><li><button class='btn-btn' type='submit' name='restaurantId' value=" + d.restaurantId + ">" + d.restaurantName + "</button></li></form>");
			})
		});
		fetch("MenuServlet")
		.then(res => res.json())
		.then(data => {
			data.forEach(d => {
				/*<form action="" method="post">
                   <li>${menu.name}&ensp;<button type="submit" value="menu.categoryId">View</button></li>
                </form>*/
				$("#mdrop").append("<form action='MenuServlet' method='post'><li><button class='btn-btn' type='submit' name='categoryId' value=" + d.category.categoryId + ">" + d.name + "</button></li></form>");
			})
		});
		$(".cart-add").click(function () {
		    var test = $(this).prev()[0];
		});
	</script>
</body>

</html>