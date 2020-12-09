<%
	String username = (String) session.getAttribute("username");
	if(username != null) {
		session.invalidate();
%>
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
<title>LogOut</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
	<div class="container">
		<div class="page-header">
			<h3>Ti sei sloggato</h3>
		</div>
		<div class="panel panel-danger">
			<div class="panel-heading">
				<p>Non sei più loggato su EquatorialForest</p>
			</div>
			<div class="panel-body">
				<p>Non eri tu?</p>
				<p>
					Effettua il login - <a href="login.jsp">Login</a>
				</p>
				<p>
					Oppure registrati - <a href="registra.jsp">Registrati</a>
				</p>
			</div>
		</div>
	</div>
<footer class="footer"><%@include file="footer.html" %></footer>
</body>
</html>
<% 
	} else {
		response.sendRedirect("accessonegato.jsp");
	}
%>