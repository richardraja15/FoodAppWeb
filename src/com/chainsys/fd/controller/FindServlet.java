package com.chainsys.fd.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.FindService;
import com.chainsys.fd.services.impl.FindServiceImpl;

/**
 * Servlet implementation class FindServlet
 */
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		ArrayList<Restaurant> restaurant = new ArrayList<>();
		FindService findService = new FindServiceImpl();
		try {
			restaurant = findService.getRestaurant(search);
			if (!restaurant.isEmpty()) {
				request.setAttribute("RESTAURANT", restaurant);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("MSG", "No results found");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
