package me.max.dao;

import java.sql.Connection;
import java.sql.SQLException;

import me.max.models.User;

public interface UserDAO {
	public User getUserByUsername(Connection con, String name) throws SQLException;
}
