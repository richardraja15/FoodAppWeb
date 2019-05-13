package com.chainsys.fd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.fd.dao.SearchDAO;
import com.chainsys.fd.model.Restaurant;

/**
 * Servlet implementation class FindServlet
 */
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindServlet() {
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
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String search =
	 * request.getParameter("search"); System.out.println(search); SearchDAO
	 * searchDAO = new SearchDAO(); ArrayList<Restaurant> restaurantName = new
	 * ArrayList<>(); try { restaurantName = searchDAO.getRestaurant(search);
	 * for(Restaurant rs:restaurantName) {
	 * System.out.println(rs.getRestaurantName()); } // if (restaurantName != null)
	 * {
	 * 
	 * request.setAttribute("RESTAURANT", restaurantName); RequestDispatcher
	 * dispatcher = request.getRequestDispatcher("Search.jsp");
	 * dispatcher.forward(request, response); // // } else { // // PrintWriter out =
	 * response.getWriter(); // out.print("no results found"); // } // menuName =
	 * searchDAO.getMenu(search); } catch (Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 */

	// System.out.println("heloo");

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		System.out.println(search);
		SearchDAO searchDAO = new SearchDAO();
		ArrayList<Restaurant> restaurantName = new ArrayList<>();
		try {
			restaurantName = searchDAO.getRestaurant(search);

			if (!restaurantName.isEmpty()) {

				request.setAttribute("RESTAURANT", restaurantName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");
				dispatcher.forward(request, response);

				for (Restaurant temp : restaurantName) {
					System.out.println(temp.getCategory().getCategoryId());
				}
			} else {

				PrintWriter out = response.getWriter();
				out.print("no results found");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
