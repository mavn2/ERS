package me.max.rest;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.max.dao.UserDAO;
import me.max.dao.UserDAOImpl;
import me.max.models.User;
import me.max.util.ConnectionUtil;

@Path("/employees")
public class EmployeeService {
	
	private UserDAO db;
	
	public EmployeeService() {
		super();
		this.db = new UserDAOImpl();
	}
	
	@GET
	@Path("/roster")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllTypedRequests() {
		ObjectMapper mapper = new ObjectMapper();

		try (Connection con = ConnectionUtil.getConnection()) {
			List<User> result = db.getAllEmployees(con);
			return mapper.writeValueAsString(result);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
