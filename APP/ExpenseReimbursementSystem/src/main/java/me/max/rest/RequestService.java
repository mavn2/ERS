package me.max.rest;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.max.dao.RequestDAO;
import me.max.dao.RequestDAOImpl;
import me.max.models.PostRIRequest;
import me.max.models.PutRiRequest;
import me.max.models.Request;
import me.max.util.ConnectionUtil;

@Path("/ers")
public class RequestService {

	private RequestDAO db;

	public RequestService() {
		super();
		this.db = new RequestDAOImpl();
	}

	// Get All requests of defined type
	@GET
	@Path("/all/{type}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllTypedRequests(@PathParam("type") int type) {
		ObjectMapper mapper = new ObjectMapper();

		try (Connection con = ConnectionUtil.getConnection()) {
			List<Request> result = db.getAllTypedRequests(con, type);
			return mapper.writeValueAsString(result);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// Get all requests for specified user, type optional
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserRequests(@PathParam("id") int id, @DefaultValue("0") @QueryParam("type") int type) {
		ObjectMapper mapper = new ObjectMapper();

		try (Connection con = ConnectionUtil.getConnection()) {
			if (type == 0) {
				List<Request> result = db.getUserRequests(con, id);
				return mapper.writeValueAsString(result);
			} else {
				List<Request> result = db.getUserTypedRequests(con, id, type);
				return mapper.writeValueAsString(result);
			}

		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@POST
	@Path("/request")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRequest(String body) {
		ObjectMapper mapper = new ObjectMapper();

		try (Connection con = ConnectionUtil.getConnection()) {
			PostRIRequest r = mapper.readValue(body, PostRIRequest.class);
			System.out.println(r.getuId());
			db.createRequest(con, r.getuId(), r.getAmount(), r.getrFor());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/request")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resolveRequest(String body) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(body);
		
		try (Connection con = ConnectionUtil.getConnection()) {
			PutRiRequest r = mapper.readValue(body, PutRiRequest.class);
			System.out.println(r.getuId());
			db.handleRequest(con, r.getuId(), r.getrId(), r.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}

}
