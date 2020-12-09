<jsp:useBean id="carrello" class="com.gianluca.bc.model.Carrello"
	scope="session" />
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#MenuApp">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home.jsp"><i
				class="fas fa-cannabis fa-lg"></i> EquatorialForest</a>
		</div>
		<div class="collapse navbar-collapse" id="MenuApp">
			<ul class="nav navbar-nav">
				<li><a href="utenti.jsp">Utenti</a></li>
				<li><a href="prodotti.jsp">Prodotti</a></li>
				<li><a href="ordini.jsp">Ordini</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<div class="btn-group" style="margin-top:7px;">
						<button type="button" class="btn btn-primary regist dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-user" style="margin-right:3px;"></span>  <%= session.getAttribute("admin") %> <span class="caret"></span>
						</button>
					</div>
				</li>
				<li><a href="logout.jsp"><span
						class="glyphicon glyphicon-off"></span> Logout</a></li>
			</ul>
		</div>
	</div>
</nav>