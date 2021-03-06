package me.max.rest;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.max.dao.UserDAO;
import me.max.dao.UserDAOImpl;
import me.max.models.UpdateEmployee;
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
	
	@PUT
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(String body) {
		ObjectMapper mapper = new ObjectMapper();
		
		try(Connection con = ConnectionUtil.getConnection()) {
			UpdateEmployee r = mapper.readValue(body, UpdateEmployee.class);
			db.updateUser(con, r.getId(), r.getCol(), r.getVal());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}		
		return Response.status(200).build();
	}
}
