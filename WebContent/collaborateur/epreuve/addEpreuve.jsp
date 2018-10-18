<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Test"%>
<%@ page import="bo.Utilisateur"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Création d'une épreuve</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>
		
		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<h2>Création d'une épreuve</h2>
			<div class="contenuAccueil">
				<%
					List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
					List<Utilisateur> listeUtilisateur = (ArrayList<Utilisateur>) request.getAttribute("listeUtilisateurs");
				%>
				<form action="/ProjetQCM/collaborateur/addEpreuve" method="post">
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="dateDebut">Date de début </Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="date" name="dateDebut" value="" required>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="dateFin">Date de fin </Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="date" name="dateFin" value="" required>
						</div>
					</div>

					<div class="row">
						<div class="col-12 col-sm-2">
							<Label>Candidat </Label>
						</div>
						<div class="col-12 col-sm-10">
							<select name="idUser" required>
								<option selected disabled hidden>Choisir un candidat</option>
								<%
									for (Utilisateur u : listeUtilisateur) {
								%>
								<option value="<%=u.getIdUtilistaeur()%>"><%=u.getNom() + " " + u.getPrenom()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-12 col-sm-2">
							<Label>Test </Label>
						</div>
						<div class="col-12 col-sm-10">
							<select name="idTest" required>
								<option selected disabled hidden>Choisir un test</option>
								<%
									for (Test u : listeTest) {
								%>
								<option value="<%=u.getIdTest()%>"><%=u.getLibelle()%></option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<a href="<%=request.getContextPath()%>/validerAcces"><button
									class="btn btn-secondary" type="button">Annuler</button></a>
							<button class="btn btn-primary" type="submit">Créer</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>