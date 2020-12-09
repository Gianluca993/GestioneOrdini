<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gianluca.bc.model.Utente"%>
<%@page import="com.gianluca.bc.ClientFacade"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <jsp:useBean id="utente" class="com.gianluca.bc.model.Utente" scope="session"/> --%>
<!DOCTYPE html>
<%
	String username = (String) session.getAttribute("username");
	if(username != null) {
%>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<!-- serve a rendere responsive la pagina con bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>Profilo</title>
</head>
<body>
	<jsp:include page="nav.jsp"/>
	<div class="container">
		<div class="page-header">
			<h3>Le mie informazioni</h3>
		</div>
		<div class="table-responsive">
			<table class="table table-hover" style="width:100%;">
				<thead>
					<tr>
						<th style="width:200px;">Nome</th>
						<th style="width:200px;">Cognome</th>
						<th style="width:200px;">Indirizzo</th>
						<th style="width:200px;">CAP</th>
						<th style="width:200px;">Data di Nascita</th>
						<th style="width:200px;">Username</th>
						<th style="width:200px;">Email</th>
					</tr>
				</thead>
				<tbody>
					<%
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						Utente utente = ClientFacade.getInstance().getUtenteByUsername(username);
					%>
					<tr>
						<td><%= utente.getNome() %></td>
						<td><%= utente.getCognome() %></td>
						<td><%= utente.getIndirizzo() %></td>
						<td><%= utente.getCap() %></td>
						<td><%= format.format(utente.getNascita()) %></td>
						<td><%= utente.getUsername() %></td>
						<td><%= utente.getEmail() %></td>
					</tr>
				</tbody>
			</table>
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