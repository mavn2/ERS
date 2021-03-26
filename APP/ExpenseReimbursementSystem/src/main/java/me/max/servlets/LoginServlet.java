package me.max.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.max.dao.LoginDAO;
import me.max.dao.LoginDAOImpl;
import me.max.util.ConnectionUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginDAO ld = new LoginDAOImpl();
		try (Connection con = ConnectionUtil.getConnection()){
			String n = request.getParameter("username");
			String p = request.getParameter("password");
			String t = request.getParameter("type");
			System.out.println(n + p +t);
			//Depending on login button clicked, attempt to login
			//for appropriate role w/ user inputs
			switch(t) {
			case "e":
				ld.loginEmployee(con, n, p);
				break;
			case "m":
				ld.loginManager(con, n, p);
				break;
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
