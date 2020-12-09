<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.gianluca.bc.model.Utente"%>
<%@page import="com.gianluca.bc.ClientFacade"%>
	<% 
		String username = (String)session.getAttribute("username");
		if (username == null) {
			response.sendRedirect("accessonegato.jsp");
		}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<!-- serve a rendere responsive la pagina con bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>SignUp</title>
<script src="js/convalidaMod.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<%
		Utente utente = ClientFacade.getInstance().getUtenteByUsername(username);
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	%>
		<form action="/<%= application.getServletContextName() %>/modificautente" method="POST" class="form-horizontal" id="userForm">
			
			<%-- -------- NOME ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="nome">Nome:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" name="nome" id="nome" class="form-control" value="<%= utente.getNome() %>">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoNome"></div>
			</div>
		
			<%-- -------- COGNOME ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="cognome">Cognome:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" name="cognome" id="cognome" class="form-control" value="<%= utente.getCognome() %>">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoCognome"></div>
			</div>
		
			<%-- -------- Indirizzo ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="indirizzo">Indirizzo:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-home"></i>
						</span>
						<textarea rows="1" name="indirizzo" id="indirizzo" class="form-control" style="resize:none;"><%= utente.getIndirizzo() %></textarea>
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoIndirizzo"></div>
			</div>
		
			<%-- -------- CAP ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="cap">Cap:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-home"></i>
						</span>
						<input type="text" name="cap" id="cap" class="form-control" value=<%= utente.getCap() %>>
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoCap"></div>
			</div>
		
			<%-- -------- NASCITA ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="nascita">Data di Nascita:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group date" id="dp">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-calendar"></i>
						</span>
						<input type="text" name="nascita" id="nascita" class="form-control" value="<%= sf.format(utente.getNascita()) %>">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoNascita"></div>
			</div>
			<script>
				$(function(){
					$('#dp').datepicker({
						format: 'dd/mm/yyyy',
						autoclose: true,
						startDate: '01/01/1900',
						endDate: new Date()
					}).on(
						'changeDate',
						function(e) {
							$('#userForm').bootstrapValidator(
								'revalidateField',
								'nascita'
							);
						}
					);
				});
			</script>
			
			<%-- -------- NEW PASSWORD ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="password">Password:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-lock"></i>
						</span>
						<input type="password" name="password" id="password" class="form-control" placeholder="Inserisci la nuova Password...">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoPassword"></div>
			</div>
			
			<%-- -------- OLD PASSWORD ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="password">Password:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-lock"></i>
						</span>
						<input type="password" name="oldpassword" id="oldpassword" class="form-control" placeholder="Conferma la vecchia Password...">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoOldPassword"></div>
			</div>
						
			<%-- -------- EMAIL ----------- --%>
			<div class="form-group">
				<label class="col-md-1 control-label" for="email">E-Mail:</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" name="email" id="email" class="form-control" value="<%= utente.getEmail() %>">
					</div>
				</div>
				<div class="col-md-5 control-label" id="infoEmail"></div>
			</div>
			
			<%-- --------- BOTTONE ----------- --%>
			
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					<button type=submit class="btn btn-primary regist" id="regist">Modifica&nbsp;<span class="glyphicon glyphicon-send"></span></button>
				</div>
			</div>
		</form>

	<footer class="footer"><%@include file="footer.html" %></footer>
</body>
</html>