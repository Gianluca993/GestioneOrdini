<%@page import="com.gianluca.bc.model.Immagine"%>
<%@page import="com.gianluca.bc.ClientFacade"%>
<%@page import="com.gianluca.bc.model.Articolo"%>
<%
	String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("accessonegato.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="carrello" class="com.gianluca.bc.model.Carrello"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>Acquisti</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<div class="page-header">
			<h3>Articoli da acquistare</h3>
		</div>
		<div class="ricerca">
			<form action="#" method="GET" class="form-horizontal" id="userForm">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-search"></i>
					</span> <input type="text" name="query" id="query" class="form-control"
						placeholder="Marca o Modello...">
				</div>
				<button type="submit" class="btn btn-primary regist"
					style="float: right;">Cerca</button>
			</form>
		</div>
		<p>
			Totale articoli: <strong><%=String.format("%.2f", carrello.totaleComplessivo())%>&euro;</strong>
		</p>
		<div class="table-responsive">
			<table class="table table-hover" style="width: 100%;">
				<thead>
					<tr>
						<th style="width: 200px;">Immagine</th>
						<th style="width: 200px;">Id</th>
						<th style="width: 200px;">Marca</th>
						<th style="width: 200px;">Modello</th>
						<th style="width: 200px;">Prezzo</th>
						<th style="width: 200px;">Descrizione</th>
						<th style="width: 200px;">Ordina</th>
					</tr>
				</thead>
				<tbody>
					<%
						String query = request.getParameter("query");
					Articolo[] articoli = null;
					if (query != null) {
						articoli = ClientFacade.getInstance().cercaArticoli(query);
					} else {
						articoli = ClientFacade.getInstance().getArticoli();
					}
					for (int i = 0; i < articoli.length; i++) {
						Immagine img = ClientFacade.getInstance().getImmagineById(articoli[i].getIdArticolo());
					%>

					<tr>
						<td><img alt='<%=img.getDescrizione()%>'
							src='./<%=img.getUrl()%>' class='prods'></td>
						<td><%=articoli[i].getIdArticolo()%></td>
						<td><%=articoli[i].getMarca()%></td>
						<td><%=articoli[i].getModello()%></td>
						<td><%=String.format("%.2f", articoli[i].getPrezzo())%>&euro;</td>
						<td><button type="button" class="btn btn-primary regist"
								data-toggle="modal" data-target=".desc<%=i%>">Descrizione</button>
							<div class="modal fade desc<%=i%>" tabindex="-1" role="dialog"
								aria-labelledby="myLargeModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<%=img.getDescrizione()%>
									</div>
								</div>
							</div></td>
						<td>
							<form action="/<%=application.getServletContextName()%>/aggiungi"
								method="post">
								<input type="hidden" name="id"
									value="<%=articoli[i].getIdArticolo()%>"> <input
									type="hidden" name="marca" value="<%=articoli[i].getMarca()%>">
								<input type="hidden" name="modello"
									value="<%=articoli[i].getModello()%>"> <input
									type="hidden" name="prezzo"
									value="<%=articoli[i].getPrezzo()%>">


								<button type="submit" class="btn btn-primary btn-s regist">Aggiungi
									al Carrello</button>
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
		<a href="visualizza.jsp">Vai al Carrello</a>
	</div>
	<footer><%@include file="footer.html"%></footer>


</body>
</html>