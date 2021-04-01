<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="me.max.models.User"%>
<%
User user = (User) session.getAttribute("user");
String fName = user.getFirstName();
String lName = user.getLastName();
String email = user.getEmail();
int id = user.getId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><button class="nav-link btn"
							type="button" data-bs-toggle="modal"
							data-bs-target="#requestModal">New Request</button></li>
					<li class="nav-item"><a class="nav-link"
						href="/ExpenseReimbursementSystem/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<main class="container">
		<div class="row">
			<div class="col-3" id="userInfo">
				<h5>
					<%
					out.print(fName + " " + lName);
					%>
				</h5>
				<p>
					<%
					out.print("Employee ID: " + "<span id=uId>" + id + "</span>");
					%>
				</p>
				<p>
					<%
					out.print("Email: " + email);
					%>
				</p>
			</div>
			<!-- Main Request navigation/display -->
			<div class="col-9">
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item" id="pendingTab">
						<button class="nav-link active" type="button" role="tab"
							data-bs-toggle="tab">Active</button>
					</li>
					<li class="nav-item" id="approvedTab">
						<button class="nav-link" type="button" role="tab"
							data-bs-toggle="tab">Approved</button>
					</li>
					<li class="nav-item" id="deniedTab">
						<button class="nav-link" type="button" role="tab"
							data-bs-toggle="tab">Denied</button>
					</li>
				</ul>
				<div id="display"></div>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="requestModal" tabindex="-1"
			aria-labelledby="requestModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="requestModalLabel">Request</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div>
							<div class="mb-3">
								<label for="rAmount" class="form-label">Amount</label> <input
									type="text" class="form-control" id="rAmount" name="amount">
							</div>
							<div class="mb-3">
								<label for="rFor" class="form-label">For</label> <input
									type="text" class="form-control" id="rFor" name="for">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" id="submitRequest"
							data-bs-dismiss="modal">Submit</button>

						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/employee.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
</html>