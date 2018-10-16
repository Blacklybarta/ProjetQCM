<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bo.exceptions.ListException" %>
<%@ page import="dal.DALException" %>
<!DOCTYPE html>
<head>
<%@include file="../fragments/head.jsp"%>
<title>QCM - Erreur</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../fragments/header.jsp"%>

		<!-- Menu -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
		        		<a class="nav-link" href="/ProjetQCM/connexion">Connexion</a>
		      		</li>
				</ul>
			</div>
		</nav>

		<div class="col-12">
			<p class="error"> 
				<%= request.getAttribute("error") %>
			</p>
		</div>
	</div>
	
	<%@include file="../fragments/script.jsp"%>
</body>
</html>