package me.max.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import me.max.models.Request;

public interface RequestDAO {
	public Request createRequest(Connection con, int uId, double amount, String rFor) throws SQLException;
	public ArrayList<Request> getAllTypedRequests(Connection con, int type) throws SQLException;
	public  ArrayList<Request> getUserRequests(Connection con, int uId) throws SQLException;
	public  ArrayList<Request> getUserTypedRequests(Connection con, int uId, int type) throws SQLException;
	public boolean handleRequest(Connection con, int uId, int rId, int status) throws SQLException;
}
