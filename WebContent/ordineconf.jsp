<%@page import="java.util.Enumeration"%>
<%@page import="com.gianluca.bc.model.Immagine"%>
<%@page import="com.gianluca.bc.ClientFacade"%>
<%@page import="com.gianluca.bc.model.Articolo"%>
<%
 	String username = (String)session.getAttribute("username");
	long idOrdine = (long)session.getAttribute("idOrdine");
	if(idOrdine != 0 && username != null) {
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="carrello" class="com.gianluca.bc.model.Carrello" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>Ordine confermato</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
	<div class="container">
		<div class="page-header">
			<h3>Ordine confermato</h3>
		</div>
		<p>Codice ordine: <strong><%= idOrdine %></strong></p>
		<div class="table-responsive">
			<table class="table table-hover" style="width:100%;">
				<thead>
					<tr>
						<th style="width:200px;">Marca</th>
						<th style="width:200px;">Modello</th>
						<th style="width:200px;">Prezzo</th>
						<th style="width:200px;">Quantit&agrave;</th>
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
					</tr>
					<%
						}
						session.removeAttribute("carrello");
						carrello.svuota();
					%>
				</tbody>
			</table>
		</div>
		<hr>
		<a href="acquisti.jsp">Acquista ancora</a>
	</div>
	<footer><%@include file="footer.html" %></footer>
	
	
</body>
</html>
<% 
	} else {
		response.sendRedirect("accessonegato.jsp");
	}
%>