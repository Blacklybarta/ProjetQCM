<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Question"%>
<%@ page import="bo.Theme"%>
<%@ page import="bo.Utilisateur"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
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

		<!-- Menu -->
		<%@include file="../fragments/menu.jsp"%>

		<div class="col-12">
			<h2>Accueil collaborateur</h2>
			<%
				List<Question> listeQuestion = (ArrayList<Question>) request.getAttribute("listeQuestions");
				List<Utilisateur> listeUtilisateur = (ArrayList<Utilisateur>) request.getAttribute("listeUtilisateurs");
				List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
			%>
			<div class="row">
				<div class="col-12 col-sm-6">
					<div class="card">
					  	<h5 class="card-header">Liste des questions</h5>
					  	<div class="card-body">
					    	<select multiple
								style="height: 300px; width: 100%;">
								<% for (Question u : listeQuestion) { %>
									<option><%=u.getEnonce()%></option>
								<% } %>
							</select>
					  	</div>
					</div>
				</div>

				<div class="col-12 col-sm-6">
					<div class="card">
					  	<h5 class="card-header">Liste des utilisateurs</h5>
					  	<div class="card-body">
					    	<select multiple
								style="height: 300px; width: 100%;">
								<% for (Utilisateur u : listeUtilisateur) { %>
									<option><%=u.getPrenom() + " " + u.getNom()%></option>
								<% } %>
							</select>
					  	</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-sm-6">
					<div class="card">
					  	<h5 class="card-header">Liste des th�mes</h5>
					  	<div class="card-body">
					    	<select multiple
								style="height: 300px; width: 100%;">
								<% for (Theme u : listeTheme) { %>
									<option><%=u.getLibelle()%></option>
								<% } %>
							</select>
					  	</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../fragments/script.jsp"%>
</body>
</html>