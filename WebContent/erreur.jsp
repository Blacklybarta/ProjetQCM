<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bo.exceptions.ListException" %>
<%@ page import="dal.DALException" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Carnet de bord - Erreur</title>
</head>
<body>
	<!-- Header -->
	<header>
  		<p class="titre1">Carnet de bord</p>
  		<p class="titre2">Erreur</p>
	</header>
	<div class="col-xs-12 col-sm-9">
		
		<p class="error"> 
		<%= request.getAttribute("error") %>
		</p>
		
		
	</div>
    <div class="col-xs-12 col-sm-3">

      	<!-- Menu -->
      	<div class="menu">
      		<h3>Menu</h3>
      		<a href="/ProjetQCM">Accueil</a></br>
      		<a href="/ProjetQCM/connexion">Connexion</a>
      	</div>
	</div>
</body>
</html></html>