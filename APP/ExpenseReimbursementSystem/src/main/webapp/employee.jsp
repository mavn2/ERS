<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="me.max.models.User" %>
<%
	User user = (User)session.getAttribute("user");
	String fName = user.getFirstName();
	int id = user.getId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
<%out.print(fName); %>
</body>
</html>