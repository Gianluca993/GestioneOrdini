<%
	String username = (String)session.getAttribute("admin");
	if(username == null) {
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
<title>Admin Home</title>
</head>
<body>
	<jsp:include page="adnav.jsp" />
<div class="page-header" style="text-align:center;">
	<h1>Benvenuto</h1>
</div>

</body>
</html>