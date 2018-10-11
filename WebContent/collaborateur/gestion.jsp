<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../fragments/head.jsp"%>
	<title>QCM - Accueil collaborateur</title>
</head>
<body>
	<div class="container">
	<!-- Header -->
		<%@include file="../fragments/header.jsp"%>

		<div class="col-xs-12 col-sm-9">
			<h2>Accueil collaborateur</h2>
			En tant que collaborateurs vous pouvez :
			<ul>
				<li>Créer un test</li>
				<li>Créer des questions</li>
				<li>Voir l'historique de vos tests</li>
			</ul>
		</div>
		
		<div class="col-xs-12 col-sm-3">
			<!-- Menu -->
			<%@include file="../fragments/menu.jsp"%>
		</div>
	</div>
</body>
</html>