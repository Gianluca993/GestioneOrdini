<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.IOException"%>
<%@page import="com.gianluca.architecture.dao.DAOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="CDN.html"%>
<!-- serve a rendere responsive la pagina con bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Oh noooooooo!</title>
</head>
<body>
	<jsp:include page="nav.jsp"/>
	<div class="container">
		<div class="page-header">
			<h3>Oh noooo! Qualcosa è andato storto!<span style='font-size:25px;'>&#128546;</span></h3>
		</div>
		<div class="panel panel-danger">
		<%
			if(exception instanceof ClassNotFoundException) {
		%>
			<div class="panel-heading">
				<p>Intercettata una ClassNotFoundException</p>
				<p><%= exception.getMessage() %></p>
			</div>
			<div class="panel-body">
				<p>Ci dispiace molto!</p>
				<p><%= exception.getCause() %></p>
				<% exception.printStackTrace(new PrintWriter(out)); %>
				<p>
					Segnala un problema - <a href="mailto:admin@sito.it">Lamentati</a>
				</p>
				<p>
					Oppure torna alla pagina precedente -
					<input type="button" class="btn btn-default" value="Ritorna" onclick="window.history.back()">
				</p>
			</div>
		<%
			} else if(exception instanceof DAOException) {
		%>
			<div class="panel-heading">
				<p>Intercettata una DAOException</p>
				<p><%= ((DAOException)exception).getMessage() %></p>
			</div>
			<div class="panel-body">
				<p>Ci dispiace molto!</p>
				<p><%= exception.getCause() %></p>
				<% exception.printStackTrace(new PrintWriter(out)); %>
				<p>
					Segnala un problema - <a href="mailto:admin@sito.it">Lamentati</a>
				</p>
				<p>
					Oppure torna alla pagina precedente -
					<input type="button" class="btn btn-default" value="Ritorna" onclick="window.history.back()">
				</p>
			</div>
		<%
			} else if(exception instanceof IOException) {
		%>
			<div class="panel-heading">
				<p>Intercettata una IOException</p>
				<p><%= exception.getMessage() %></p>
			</div>
			<div class="panel-body">
				<p>Ci dispiace molto!</p>
				<p><%= exception.getCause() %></p>
				<% exception.printStackTrace(new PrintWriter(out)); %>
				<p>
					Segnala un problema - <a href="mailto:admin@sito.it">Lamentati</a>
				</p>
				<p>
					Oppure torna alla pagina precedente -
					<input type="button" class="btn btn-default" value="Ritorna" onclick="window.history.back()">
				</p>
			</div>
		<%
			} else {
		%>
			<div class="panel-heading">
				<p>Eccezione non prevista</p>
				<p><%= exception.getMessage() %></p>
			</div>
			<div class="panel-body">
				<p>Ci dispiace molto!</p>
				<p><%= exception.getCause() %></p>
				<% exception.printStackTrace(new PrintWriter(out)); %>
				<p>
					Segnala un problema - <a href="mailto:admin@sito.it">Lamentati</a>
				</p>
				<p>
					Oppure torna alla pagina precedente -
					<input type="button" class="btn btn-default" value="Ritorna" onclick="window.history.back()">
				</p>
			</div>
		<%
			}
		%>
		</div>
	</div>
	<footer><%@include file="footer.html" %></footer>
</body>
</html>