package me.max.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import me.max.models.User;

public interface UserDAO {
	public User getUserByUsername(Connection con, String name) throws SQLException;
	public boolean updateUser(Connection con, int uId, String col, String value) throws SQLException;
	public List<User> getAllEmployees(Connection con) throws SQLException;
}
