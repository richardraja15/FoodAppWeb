package com.chainsys.fd.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.chainsys.fd.dao.MenuDAO;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.RestaurantService;
import com.chainsys.fd.services.impl.RestaurantServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cardList = request.getParameter("CartList");
		JSONArray jsonArray = new JSONArray(cardList);
		Menu menu = new Menu();
		MenuDAO menuDAO = new MenuDAO();
		List<Map<String, String>> foodListWithQuantity = new ArrayList<>();
		HttpSession httpSession = request.getSession(false);
		int restaurantId = (int) httpSession.getAttribute("RESTAURANTID");
		RestaurantService restaurantService = new RestaurantServiceImpl();
		Restaurant restaurant = new Restaurant();
		double totalAmount=0;
		try {
			restaurant = restaurantService.getRestaurantById(restaurantId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.getJSONObject(i);
			int quantity = jsonObject.getInt("quantity");
			if (quantity > 0) {
				try {
					Map<String, String> foodList = new HashMap<String, String>();
					menu = menuDAO.getMenuById(Integer.parseInt(jsonObject.getString("menuId")));
					menu.setQuantity(quantity);
					menu.setMenuId(Integer.parseInt(jsonObject.getString("menuId")));
					foodList.put("restaurantId", String.valueOf(restaurantId));
					foodList.put("restaurantName", restaurant.getRestaurantName());
					foodList.put("menuId", String.valueOf(menu.getMenuId()));
					foodList.put("menuName", menu.getName());
					foodList.put("price", String.valueOf(menu.getPrice()));
					foodList.put("quantity", String.valueOf(menu.getQuantity()));
					double totalPrice = menu.getPrice() * menu.getQuantity();
					foodList.put("totalPrice", String.valueOf(totalPrice));
					totalAmount=totalAmount+totalPrice;
					foodListWithQuantity.add(foodList);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Map<String, String> foodCharges = new HashMap<String, String>();
		double restaurantCharges=(totalAmount * 0.06);
		double finalAmount=(totalAmount+restaurantCharges+25);
		foodCharges.put("totalAmount", String.valueOf(totalAmount));
		foodCharges.put("restaurantCharges",String.valueOf(round(restaurantCharges, 2)));
		foodCharges.put("finalAmount", String.valueOf(finalAmount));
		List<Map<String, String>> foodListCharges = new ArrayList<>();
		foodListCharges.add(foodCharges);
		HttpSession session = request.getSession();
		session.setAttribute("FOODLIST", foodListWithQuantity);
		session.setAttribute("FOODCHARGES", foodListCharges);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String orderList = gson.toJson(foodListWithQuantity);
		response.getWriter().write(orderList);
	}
}
