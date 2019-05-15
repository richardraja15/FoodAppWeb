package com.chainsys.fd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.fd.dao.RestaurantDAO;
import com.chainsys.fd.dao.SearchDAO;
import com.chainsys.fd.model.Menu;

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
		int restaurantId=Integer.parseInt(request.getParameter("restaurantId"));
		System.out.println("restid"+restaurantId);
		HttpSession session = request.getSession();
		session.setAttribute("RESTAURANTID", restaurantId);
		
		SearchDAO searchDAO = new SearchDAO();
		ArrayList<Menu> menuName = new ArrayList<>();
		RestaurantDAO restaurantDAO=new  RestaurantDAO();
		int categoryId = 0;
		try {
			categoryId=restaurantDAO.getCategoryByRestaurant(restaurantId);
			System.out.println("cat"+categoryId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Integer> menuId=new ArrayList<Integer>();
		try {
			menuName = searchDAO.getMenu(categoryId);
		
			for(Menu item:menuName)
			{
				menuId.add(item.getMenuId());
				System.out.println("mid"+item.getMenuId());
			}
			
			/*for(Integer temp:menuId) {
				System.out.println("Id"+temp);
			}*/
			if (!menuName.isEmpty()) {
				request.setAttribute("menuId", menuId);
				request.setAttribute("MENUNAME", menuName);
				RequestDispatcher dispatcher = request.getRequestDispatcher("View.jsp");
				dispatcher.forward(request, response);

				/*for (Menu temp : menuName) {
					System.out.println(temp.getMenuId());
				}*/
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
