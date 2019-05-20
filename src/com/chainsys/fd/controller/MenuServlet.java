package com.chainsys.fd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.MenuService;
import com.chainsys.fd.services.impl.MenuServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MenuService menuService = new MenuServiceImpl();
		ArrayList<Menu> menuItems = new ArrayList<>();
		try {
			menuItems = menuService.getMenuItems();
			request.setAttribute("MENUITEMS", menuItems);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String menuList = gson.toJson(menuItems);
			response.getWriter().write(menuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		ArrayList<Restaurant> restaurantName = new ArrayList<>();
		MenuService menuService = new MenuServiceImpl();
		try {
			restaurantName = menuService.getRestaurantByCategory(categoryId);
			request.setAttribute("RESTAURANT", restaurantName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
