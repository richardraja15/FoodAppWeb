package com.chainsys.fd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.fd.dao.MenuDAO;
import com.chainsys.fd.model.Menu;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Map<String, String>> foodListWithQuantity = null;
		HttpSession httpSession = request.getSession(false);
		String menuId = request.getParameter("menuId");
		String quantity = request.getParameter("quantity");
		System.out.println("menuID" + menuId + "quantity" + quantity);
		Menu menu = new Menu();
		MenuDAO menuDAO = new MenuDAO();
		try {
			menu = menuDAO.getMenuById(Integer.parseInt(menuId));

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> foodList = new HashMap<String, String>();
		int restaurantId = (int) httpSession.getAttribute("RESTAURANTID");
		foodList.put("restaurantId", String.valueOf(restaurantId));
		foodList.put("menuId", menuId);
		foodList.put("menuName", menu.getName());
		foodList.put("price", String.valueOf(menu.getPrice()));
		foodList.put("quantity", quantity);
		double totalPrice = menu.getPrice() * Integer.parseInt(quantity);
		foodList.put("totalPrice", String.valueOf(totalPrice));

		if (httpSession.getAttribute("OrderedList") == null) {
			foodListWithQuantity = new ArrayList<>();
			foodListWithQuantity.add(foodList);
		} else {
			foodListWithQuantity = (List<Map<String, String>>) httpSession.getAttribute("OrderedList");
			foodListWithQuantity.add(foodList);
		}

		httpSession.setAttribute("OrderedList", foodListWithQuantity);
		for (Map<String, String> temp : foodListWithQuantity) {
			System.out.println(temp);
		}

		/*
		 * RequestDispatcher dispatcher=request.getRequestDispatcher("View.jsp");
		 * dispatcher.forward(request, response);
		 */

		response.getWriter().write(String.valueOf(restaurantId));

	}

}
