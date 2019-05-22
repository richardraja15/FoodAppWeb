package com.chainsys.fd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.fd.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(false);
		UserDAO userDAO = new UserDAO();
		try {
		if (userDAO.validateLogin(userId, password)) {

		session.setAttribute("ID", userId);
		List<Map<String, String>> foodListWithQuantity = new ArrayList<>();
		List<Map<String, String>> foodListWithCharges = new ArrayList<>();
		foodListWithQuantity = (List<Map<String, String>>) session.getAttribute("FOODLIST");
		foodListWithCharges = (List<Map<String, String>>) session.getAttribute("FOODCHARGES");
		request.setAttribute("FOODLIST", foodListWithQuantity);
		request.setAttribute("FOODCHARGES", foodListWithCharges);
		RequestDispatcher rd = request.getRequestDispatcher("Payment.jsp");
		rd.forward(request, response);
		} else {
		request.setAttribute("ERROR_MSG", "Invalid UserName and Password");
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserLogin.jsp");
		dispatcher.forward(request, response);
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}









