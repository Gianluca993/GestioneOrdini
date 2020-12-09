<%@page import="com.gianluca.bc.AdminFacade"%>
<%@page import="com.gianluca.bc.model.Report"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String username = (String) session.getAttribute("admin");
if (username == null) {
	response.sendRedirect("accessonegato.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="../CDN.html"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/style.css">
<title>Acquisti</title>
</head>
<body>
	<jsp:include page="./adnav.jsp" />
	<div class="container">
		<div class="page-header" style="text-align:center;">
			<h3>Ordini effettuati</h3>
		</div>
		<div class="ricerca" style="width: 50%; margin-left: 25%; margin-bottom:50px;">
			<form action="#" method="GET" class="form-horizontal" id="userForm">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-search"></i>
					</span> <input type="text" name="query" id="query" class="form-control"
						placeholder="Nome, cognome o mail...">
				</div>
				<button type="submit" class="btn btn-primary regist"
					style="float: right;">Cerca</button>
			</form>
		</div>

		<div class="table-responsive">
			<table class="table table-hover" style="width: 100%;">
				<thead>
					<tr>
						<th style="width: 200px;">Username</th>
						<th style="width: 200px;">Email</th>
						<th style="width: 200px;">ID Ordine</th>
						<th style="width: 200px;">Data</th>
						<th style="width: 200px;">Totale</th>
						<th style="width: 200px;">Quantita</th>
						<th style="width: 200px;">Elimina</th>
					</tr>
				</thead>
				<tbody>
					<%
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					String query = request.getParameter("query");
					Report[] reports = null;
					if (query == null) {
						reports = AdminFacade.getInstance().getReports();
					} else {
						reports = AdminFacade.getInstance().searchReports(query);
					}
					for (int i = 0; i < reports.length; i++) {
					%>
<!-- 						utente.username, email, ordine.id_ordine, data, totale, sum(quantita) -->
					<tr>
						<td><%=reports[i].getUsername()%></td>
						<td><%=reports[i].getEmail()%></td>
						<td><%=reports[i].getIdOrdine()%></td>
						<td><%=format.format(reports[i].getData())%></td>
						<td><%=reports[i].getTotale()%></td>
						<td><%=reports[i].getQuantita()%></td>	
						<td>
							<button type="button" class="btn btn-danger btn-xs remove" data-toggle="modal"
								data-target="#delform<%=i%>">Elimina</button> <%-- ELIMINA --%>
							<div class="modal fade" id="delform<%=i%>" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Sei
												sicuro?</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<form action="./deleteordine" method="POST">
												<%-- ID --%>
												<input type="hidden" class="form-control" name="id" id="id"
													value="<%=reports[i].getIdOrdine()%>" required>
												<button type="submit" class="btn btn-danger remove">Elimina</button>
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Close</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>

					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>