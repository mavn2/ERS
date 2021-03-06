package me.max.servlets.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.max.dao.LoginDAO;
import me.max.dao.LoginDAOImpl;
import me.max.models.User;
import me.max.util.ConnectionUtil;
/**
 * Servlet implementation class LoginServlet
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prep to handle response-print error or save user info to session
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		LoginDAO ld = new LoginDAOImpl();
		try (Connection con = ConnectionUtil.getConnection()) {
			String n = request.getParameter("username");
			String p = request.getParameter("password");
			String t = request.getParameter("type");
			// Depending on login button clicked, attempt to login
			// for appropriate role w/ user inputs
			switch (t) {
			// If login succeeds, create User obj and store w/ session
			// Then fwd user to appropriate page.
			// Otherwise print error
			case "e":
				if (ld.loginEmployee(con, n, p) == true) {
					RequestDispatcher rd = request.getRequestDispatcher("/./EmployeeHome");
					User user = User.loginUser(n);
					session.setAttribute("user", user);
					rd.forward(request, response);
					break;
				}
				;
			case "m":
				if (ld.loginManager(con, n, p) == true) {
					RequestDispatcher rd = request.getRequestDispatcher("/./ManagerHome");
					User user = User.loginUser(n);
					session.setAttribute("user", user);
					rd.forward(request, response);
					break;
				}
				;

			default:
				out.print("<p>Login attempt failed.<p>");
				RequestDispatcher rd = request.getRequestDispatcher("/./index.html");
				rd.include(request, response);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
