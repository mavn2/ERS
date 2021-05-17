package me.max.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface LoginDAO {
	public boolean loginEmployee(Connection con, String username, String password) throws SQLException;
	public boolean loginManager(Connection con, String username, String password) throws SQLException;

}
