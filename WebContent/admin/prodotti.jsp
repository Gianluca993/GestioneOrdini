
<%@page import="com.gianluca.bc.model.Immagine"%>
<%@page import="com.gianluca.bc.ClientFacade"%>
<%@page import="com.gianluca.bc.model.Articolo"%>
<%
	String username = (String) session.getAttribute("admin");
if (username == null) {
	response.sendRedirect("../accessonegato.jsp");
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
<title>Gestione prodotti</title>
</head>
<body>
	<jsp:include page="adnav.jsp" />
	<div class="page-header" style="text-align: center;">
		<h1>Prodotti</h1>
	</div>

	<div class="ricerca" style="width: 50%; margin-left: 25%; margin-bottom:50px;">
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
	<p style="text-align:center;"><button type="button" class="btn btn-primary regist" data-toggle="modal" data-target="#creaform">Crea un nuovo prodotto</button></p>
	  <div class="modal fade" id="creaform" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Aggiungi un Prodotto</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form enctype="multipart/form-data" action="./modificaprod" method="POST">
        <%-- Immagine --%>
          <div class="form-group">
            <label for="immagine" class="col-form-label">Immagine:</label>
            <input type="file" class="form-control" name="immagine" id="immagine" accept="image/*">
          </div>
        <%-- Descrizione --%>
          <div class="form-group">
            <label for="descrizione" class="col-form-label">Descrizione:</label>
            <textarea class="form-control" name="descrizione" id="descrizione" placeholder="Descrizione..."  style="resize:none;"></textarea>
          </div>
        <%-- Marca --%>
          <div class="form-group">
            <label for="marca" class="col-form-label">Marca:</label>
            <input type="text" class="form-control" name="marca" id="marca"  placeholder="Marca..." required>
          </div>
        <%-- Modello --%>
          <div class="form-group">
            <label for="modello" class="col-form-label">Modello:</label>
            <input type="text" class="form-control" name="modello" id="modello"  placeholder="Modello..." required>
          </div>
        <%-- Prezzo --%>
          <div class="form-group">
            <label for="prezzo" class="col-form-label">Prezzo:</label>
            <input type="number" class="form-control" name="prezzo" id="prezzo"  placeholder="Prezzo..." required>
          </div>
        <%-- ID --%>
            <input type="hidden" class="form-control" name="id" id="id" value="0" required>
        <button type="submit" class="btn btn-primary regist">Crea</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

	<div class="row">
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
			if(img == null) {
				img = new Immagine();
			}
	%>
	
  <div class="col-sm-6 col-md-5" style="margin-left:50px; margin-right:50px; margin-bottom:50px; max-width:400px; max-height:250px;">
    <div class="thumbnail" style="text-align:center;">
      <img src="../<%= img.getUrl() %>" alt="<%= img.getDescrizione() %>" style="width:100px">
      <div class="caption">
        <h3><%= articoli[i].getMarca() + " " + articoli[i].getModello() %></h3>
        <p><%= img.getDescrizione() %></p>
        <p><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modform<%= i %>">Modifica</button> <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delform<%= i %>">Elimina</button></p>
      </div>
    </div>
  </div>
  <%-- MODIFICA --%>
  <div class="modal fade" id="modform<%= i %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modifica il prodotto</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form enctype="multipart/form-data" action="./modificaprod" method="POST">
        <%-- Immagine --%>
          <div class="form-group">
            <label for="immagine" class="col-form-label">Immagine:</label>
            <input type="file" class="form-control" name="immagine" id="immagine" value="<%= img.getUrl() %>" accept="image/*">
          </div>
        <%-- Marca --%>
          <div class="form-group">
            <label for="descrizione" class="col-form-label">Descrizione:</label>
            <textarea class="form-control" name="descrizione" id="descrizione" style="resize:none;"><%= img.getDescrizione() %></textarea>
          </div>
        <%-- Marca --%>
          <div class="form-group">
            <label for="marca" class="col-form-label">Marca:</label>
            <input type="text" class="form-control" name="marca" id="marca" value="<%= articoli[i].getMarca() %>" required>
          </div>
        <%-- Marca --%>
          <div class="form-group">
            <label for="modello" class="col-form-label">Modello:</label>
            <input type="text" class="form-control" name="modello" id="modello" value="<%= articoli[i].getModello() %>" required>
          </div>
        <%-- Marca --%>
          <div class="form-group">
            <label for="prezzo" class="col-form-label">Prezzo:</label>
            <input type="number" class="form-control" name="prezzo" id="prezzo" value="<%= articoli[i].getPrezzo() %>" required>
          </div>
        <%-- ID --%>
            <input type="hidden" class="form-control" name="id" id="id" value="<%= articoli[i].getIdArticolo() %>" required>
        <button type="submit" class="btn btn-primary regist">Modifica</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<%-- ELIMINA --%>
<div class="modal fade" id="delform<%= i %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Sei sicuro?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="./deleteprod" method="POST">
        <%-- ID --%>
            <input type="hidden" class="form-control" name="id" id="id" value="<%= articoli[i].getIdArticolo() %>" required>
        <button type="submit" class="btn btn-danger remove">Elimina</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </form>
      </div>
    </div>
  </div>
</div>
	<%
		}
	%>
</div>
</body>
</html>