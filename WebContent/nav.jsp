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
			<%
				String user = (String) session.getAttribute("username");
			if (user == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="registra.jsp"> <span
						class="glyphicon glyphicon-user"></span> Registrati
				</a></li>
				<li><a href="login.jsp"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav">
				<li><a href="acquisti.jsp">Acquisti</a></li>
				<li><a href="visualizza.jsp">Riepilogo ordine</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="visualizza.jsp"> <span
						class="glyphicon glyphicon-shopping-cart">&nbsp;</span> <span
						class="badge"><%=carrello.totaleArticoli()%></span></a></li>
				<li>
					<div class="btn-group" style="margin-top:7px;">
						<button type="button" class="btn btn-primary regist dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-user" style="margin-right:3px;"></span>  <%= session.getAttribute("username") %> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" style="background: #034915;">
							<li><a href="infoutente.jsp" style="color:#efefef;"><span class="glyphicon glyphicon-info-sign"></span>  Info Utente</a></li>
							<li role="separator" class="divider" style="background:rgba(70,103,54,1);"></li>
							<li><a href="modificadati.jsp" style="color:#efefef;"><span class="glyphicon glyphicon-cog"></span>  Modifica Dati</a></li>
						</ul>
					</div>
				</li>
				<li><a href="logout.jsp"><span
						class="glyphicon glyphicon-off"></span> Logout</a></li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</nav>