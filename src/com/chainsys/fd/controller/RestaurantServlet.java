package com.chainsys.fd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.RestaurantService;
import com.chainsys.fd.services.impl.RestaurantServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RestaurantService restaurantService = new RestaurantServiceImpl();
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		try {
			restaurants = restaurantService.getRestaurant();
			request.setAttribute("RESTAURANTS", restaurants);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String restaurantList = gson.toJson(restaurants);
			response.getWriter().write(restaurantList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
		HttpSession session = request.getSession();
		session.setAttribute("RESTAURANTID", restaurantId);
		ArrayList<Menu> menuName = new ArrayList<>();
		RestaurantService restaurantService = new RestaurantServiceImpl();
		int categoryId = 0;
		try {
			categoryId = restaurantService.getCategoryByRestaurant(restaurantId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		List<Integer> menuId = new ArrayList<Integer>();
		try {
			menuName = restaurantService.getMenu(categoryId);
			for (Menu item : menuName) {
				menuId.add(item.getMenuId());
			}
			request.setAttribute("menuId", menuId);
			request.setAttribute("MENUNAME", menuName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewMenu.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
