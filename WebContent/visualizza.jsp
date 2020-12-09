<%
	String username = (String)session.getAttribute("username");
	if(username != null) {
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Enumeration"%>
<jsp:useBean id="carrello" class="com.gianluca.bc.model.Carrello" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>Carrello</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
	<div class="container">
		<div class="page-header">
			<h3>Il mio carrello</h3>
		</div>
		<p>Totale articoli: <strong><%= String.format("%.2f", carrello.totaleComplessivo()) %>&euro;</strong></p>
		<div class="table-responsive">
			<table class="table table-hover" style="width:100%;">
				<thead>
					<tr>
						<th style="width:200px;">Marca</th>
						<th style="width:200px;">Modello</th>
						<th style="width:200px;">Parziale</th>
						<th style="width:200px;">Quantit&agrave;</th>
						<th style="width:200px;">Rimuovi</th>
					</tr>
				</thead>
				<tbody>
					<%
						Enumeration<String[]> prodotti = carrello.listaProdotti();
						while(prodotti.hasMoreElements()) {
							String[] prodotto = prodotti.nextElement();
					%>
					<tr>
						<td><%= prodotto[1] %></td>
						<td><%= prodotto[2] %></td>
						<td><%= String.format("%.2f", carrello.totaleParziale(prodotto[4])) %>&euro;</td>
						<td><%= prodotto[3] %></td>
						<td>
							<form action="/<%= application.getServletContextName() %>/rimuovi?id=<%= prodotto[4] %>" method="post">			
								<button type="submit" class="btn btn-danger btn-s remove">Rimuovi</button>
							</form>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<hr>
		<a href="acquisti.jsp">Continua ad acquistare</a>
		<%
			if(carrello.totaleArticoli() != 0) {
		%>
		<div class="panel panel-success" style="magin-top:50px;">
			<div class="panel-heading">
				<h4><strong>Procedi all'Ordine</strong></h4>
				<div style="text-align:right;"> Totale articoli: <%= carrello.totaleArticoli() %></div>	
			</div>
			<div class="panel-body">
				<form action="/<%= application.getServletContextName() %>/conferma" method="post">
					<button type="submit" class="btn btn-primary regist">
						&#10003; Ordina
					</button>
				</form>
			</div>
		</div>
		<%
			}
		%>
	</div>
	<footer><%@include file="footer.html" %></footer>
	
	
</body>
</html>
<% 
	} else {
		response.sendRedirect("accessonegato.jsp");
	}
%>