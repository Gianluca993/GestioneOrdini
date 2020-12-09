<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<!-- serve a rendere responsive la pagina con bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>LogIn</title>
</head>
<body>
	<jsp:include page="nav.jsp" />

		<form action="/<%= application.getServletContextName() %>/login" method="POST" class="form-horizontal" id="userForm">
			
			<%-- -------- USERNAME ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="username">Username:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" name="username" id="username" class="form-control" placeholder="Username...">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoUsername"></div>
			</div>
			
			<%-- -------- PASSWORD ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="password">Password:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-lock"></i>
						</span>
						<input type="password" name="password" id="password" class="form-control" placeholder="Password...">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoPassword"></div>
			</div>
			
			<%-- --------- BOTTONE ----------- --%>
			
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					<button type=submit class="btn btn-primary regist" id="regist">Login&nbsp;<span class="glyphicon glyphicon-send"></span></button>
				</div>
			</div>
		</form>

	<footer class="footer"><%@include file="footer.html" %></footer>
</body>
</html>