package me.max.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.max.models.User;

public class UserDAOImpl implements UserDAO{

	@Override
	public User getUserByUsername(Connection con, String name) throws SQLException{
		String sql = "SELECT * FROM employees WHERE e_name = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);

		// Set ? in sql string = to username passed in as param
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			User result = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			return result;
		}
		else{
			throw new SQLException("Database could not be reached.");
		}
	}
	
}
