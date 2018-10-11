<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../fragments/head.jsp"%>
	<title>QCM - Accueil</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../fragments/header.jsp"%>

		<h2>Bienvenue sur la page d'accueil des épreuves</h2>
					
		<p>Veuillez vous connecter pour passer ou administrer une épreuve.</p>
		<a href="/ProjetQCM/connexion"><button class="btn btn-primary" type="button">Se connecter</button></a>
	</div>
	
	<%@include file="../fragments/script.jsp"%>
</body>
</html>