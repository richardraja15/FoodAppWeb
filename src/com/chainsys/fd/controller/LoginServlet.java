package com.chainsys.fd.controller;

import java.io.IOException;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		try {
			if (userDAO.validateLogin(userId, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("ID", userId);
				RequestDispatcher rd = request.getRequestDispatcher("Payement.jsp");
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
