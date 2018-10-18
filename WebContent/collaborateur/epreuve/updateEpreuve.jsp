<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Utilisateur"%>
<%@ page import="bo.Test"%>
<%@ page import="bo.Epreuve"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Modification d'une épreuve</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<div class="col-12">
			<%
				Epreuve epreuve = (Epreuve) request.getAttribute("epreuve");
				List<Epreuve> listeEpreuve = (ArrayList<Epreuve>) request.getAttribute("listeEpreuves");
				List<Utilisateur> listeUtilisateur = (ArrayList<Utilisateur>) request.getAttribute("listeUtilisateurs");
				List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			%>
			<h2>Modification d'une épreuve</h2>
			<%
				if (epreuve == null) {
			%>
			<p>Sélectionnez l'épreuve que vous souhaitez modifier.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updateEpreuve" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idEpreuve" required>
							<option selected disabled hidden>Choisir une épreuve</option>
							<%
								for (Epreuve u : listeEpreuve) {
							%>
							<option value="<%=u.getIdEpreuve()%>"><%=u.getTest().getLibelle() + " - " + u.getDateDebutValidite()%></option>
							<%
								}
							%>
						</select> <input type="hidden" name="select" value="true" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/validerAcces"><button
								class="btn btn-secondary" type="button">Retour</button></a>
						<button class="btn btn-primary" type="submit">Sélectionner</button>
					</div>
				</div>
			</form>
			<%
				} else {
			%>
			<p>Renseignez les informations de l'épreuve.</p>
			<form action="/ProjetQCM/collaborateur/updateEpreuve" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="dateDebut">Date de début </Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="date" name="dateDebut"
							value="<%=df.format(epreuve.getDateDebutValidite().getTime())%>"
							required>
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="dateFin">Date de fin </Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="date" name="dateFin"
							value="<%=df.format(epreuve.getDateFinValidite().getTime())%>"
							required>
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
				<input type="hidden" name="update" value="true" /> <input
					type="hidden" name="id" value="<%=epreuve.getIdEpreuve()%>" />
				<div class="row">
					<div class="col-12">
						<a
							href="<%=request.getContextPath()%>/collaborateur/updateEpreuve"><button
								class="btn btn-secondary" type="button">Annuler</button></a>
						<button class="btn btn-primary" type="submit">Modifier</button>
					</div>
				</div>

			</form>
			<%
				}
			%>
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>