package me.max.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Considered a single method with int role as a param, but hardcoding
// seems more secure-which in this case seems more important than reusability
public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean loginEmployee(Connection con, String username, String password) throws SQLException {
		String sql = "SELECT * FROM employees WHERE e_name = ? AND e_pass = ?";
		PreparedStatement ps = con.prepareStatement(sql);

		// Set ? in sql string = to username passed in as param
		ps.setString(1, username);
		ps.setString(2, password);

		// Query the database, and store results
		ResultSet rs = ps.executeQuery();

		// If matching result found, return true
		if (rs.next()) {
			return true;
		}

		// If above evaluates to false (no results)
		return false;
	}

	@Override
	public boolean loginManager(Connection con, String username, String password) throws SQLException {
		String sql = "SELECT * FROM employees WHERE e_name = ? AND e_pass = ? AND e_role = 2";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, username);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		}

		return false;
	}

}
