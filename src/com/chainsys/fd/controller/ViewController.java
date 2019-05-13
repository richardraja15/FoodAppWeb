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
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;

/**
 * Servlet implementation class ViewController
 */
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewController() {
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
		// TODO Auto-generated method stub
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId);
		SearchDAO searchDAO = new SearchDAO();
		ArrayList<Menu> menuName = new ArrayList<>();
		try {
			menuName = searchDAO.getMenu(categoryId);

			if (!menuName.isEmpty()) {

				request.setAttribute("MENUNAME", menuName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");
				dispatcher.forward(request, response);

				for (Menu temp : menuName) {
					System.out.println(temp.getName());
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
