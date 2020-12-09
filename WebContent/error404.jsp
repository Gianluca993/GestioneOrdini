<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="CDN.html"%>
<!-- serve a rendere responsive la pagina con bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>Errore 404</title>
</head>
<body>
<jsp:include page="nav.jsp"/>
	<div class="container">
		<div class="page-header">
			<h3>Oh-oh! La pagina che cerchi non è qui</h3>
		</div>
		<div class="panel panel-danger">
			<div class="panel-heading">
				<p>Impossibile trovare la pagina che cercavi</p>
			</div>
			<div class="panel-body">
				<p>Ci dispiace molto!</p>
				<p>
					Segnala un problema - <a href="mailto:admin@sito.it">Lamentati</a>
				</p>
				<p>
					Oppure torna alla pagina precedente -
					<input type="button" class="btn btn-default" value="Ritorna" onclick="window.history.back()">
				</p>
			</div>
		</div>
	</div>
	<footer class="footer"><%@include file="footer.html" %></footer>
</body>
</html>