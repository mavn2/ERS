package me.max.servlets.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.max.dao.RequestDAO;
import me.max.dao.RequestDAOImpl;
import me.max.models.User;
import me.max.util.ConnectionUtil;

/**
 * Servlet implementation class CreateRequest
 */
public class CreateRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequest() {
        super();
        this.db = new RequestDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get info required for db
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int id = user.getId();
		double amount = Double.parseDouble(request.getParameter("amount"));
		String rFor = request.getParameter("for");
		
		try(Connection con = ConnectionUtil.getConnection()){
			db.createRequest(con, id, amount, rFor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}