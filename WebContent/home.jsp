<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="CDN.html"%>
<title>Home Page</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="well"
		style="background: linear-gradient(135deg, rgba(203, 253, 179, 1) 0%, rgba(70, 103, 54, 1) 100%)">
		<h1 style="text-align:center; ">Benvenuto in un bellissimo Ecommerce!</h1>
		<p style="margin-left:160px;"><a class="btn btn-success btn-lg" href="registra.jsp">Registrati &raquo;</a></p>
	</div>
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="img/home/2.jpeg" alt="Los Angeles">
				</div>

				<div class="item">
					<img src="img/home/3.jpeg" alt="Chicago">
				</div>

				<div class="item">
					<img src="img/home/4.jpeg" alt="New York">
				</div>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<footer class="footer"><%@include file="footer.html"%></footer>
</body>
</html>