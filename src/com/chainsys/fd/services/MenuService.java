package com.chainsys.fd.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.fd.dao.MenuDAO;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;

/**
 * Servlet implementation class MenuService
 */
@WebServlet("/MenuService")
public class MenuService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuService() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId);
		ArrayList<Restaurant> restaurantName = new ArrayList<>();
		MenuDAO menuName = new MenuDAO();
		try {
			restaurantName = menuName.getRestaurantByMenu(categoryId);

			if (!restaurantName.isEmpty()) {

				request.setAttribute("RESTAURANTNAME", restaurantName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);

				for (Restaurant temp : restaurantName) {
					System.out.println(temp.getRestaurantName());
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
