package me.max.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import me.max.models.Request;

public class RequestDAOImpl implements RequestDAO {

	@Override
	public Request createRequest(Connection con, int uId, double amount, String rFor) throws SQLException {
		String sql = "INSERT INTO ri_requests(apply_id, ri_status, ri_amount, ri_for) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, uId);
		// Setting default state(pending) here to account for possible variance in db
		// as state ids/value could change depending on implementation of request status
		// table
		ps.setInt(2, 1);
		ps.setDouble(3, amount);
		ps.setString(4, rFor);

		// Check to ensure a row was added
		int count = ps.executeUpdate();
		// Get generated id, date from query
		ResultSet rs = ps.getGeneratedKeys();

		int id;
		Date date;

		// Set values if returned, else throw exception (creation failed)
		if (rs.next()) {
			id = rs.getInt(1);
			date = rs.getDate(7);
		} else {
			throw new SQLException("Error: Request could not be created.");
		}

		// Check to ensure a row was updated, return request object
		if (count == 1) {
			Request result = new Request(id, uId, 1, amount, rFor, date);
			return result;
		} else {
			throw new SQLException("Error: Request could not be created.");
		}
	}

	@Override
	public ArrayList<Request> getAllTypedRequests(Connection con, int type) throws SQLException {
		ArrayList<Request> result = new ArrayList<>();

		String sql = "SELECT * FROM ri_requests WHERE ri_status = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, type);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Request entry = new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5),
					rs.getString(6), rs.getDate(7));
			result.add(entry);
		}

		return result;
	}

	@Override
	public ArrayList<Request> getUserRequests(Connection con, int uId) throws SQLException {
		ArrayList<Request> result = new ArrayList<>();

		String sql = "SELECT * FROM ri_requests WHERE apply_id = ?;";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, uId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Request entry = new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5),
					rs.getString(6), rs.getDate(7));
			result.add(entry);
		}

		return result;
	}

	@Override
	public ArrayList<Request> getUserTypedRequests(Connection con, int uId, int type) throws SQLException {
		ArrayList<Request> result = new ArrayList<>();

		String sql = "SELECT * FROM ri_requests WHERE apply_id = ? AND ri_status = ?;";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, uId);
		ps.setInt(2, type);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Request entry = new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5),
					rs.getString(6), rs.getDate(7));
			result.add(entry);
		}

		return result;
	}

	@Override
	public boolean handleRequest(Connection con, int uId, int rId, int status) throws SQLException {
		String sql = "UPDATE ri_requests SET ri_status = ?, approve_id = ? WHERE id = ?;";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, status);
		ps.setInt(2, uId);
		ps.setInt(3, rId);

		// Check for result, return true of throw exception
		int count = ps.executeUpdate();

		if (count == 1) {
			return true;
		} else {
			throw new SQLException("Error: request could not be updated");
		}
	}
}
