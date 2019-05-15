package com.chainsys.fd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.fd.dao.MenuDAO;
import com.chainsys.fd.dao.RestaurantDAO;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantServlet() {
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
		RestaurantDAO restaurantDAO=new RestaurantDAO();
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		try {
			restaurants= restaurantDAO.getRestaurant();
			request.setAttribute("RESTAURANTS", restaurants);
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			String restaurantList=gson.toJson(restaurants);
			response.getWriter().write(restaurantList);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//			dispatcher.forward(request, response);
			/*for (Restaurant temp : restaurants) {
				System.out.println(temp.getRestaurantName());
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
