<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Promotion"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Ajout d'un candidat</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>
		<%
			List<Promotion> listePromotion = (ArrayList<Promotion>) request.getAttribute("listePromotions");
		%>
		<div class="col-xs-12 col-sm-9">
			<h2>Création d'un candidat</h2>
			<p>Rentrer les informations du candidat que vous souhaitez créer.</p>
			<div class="contenuAccueil">
				<form action="/ProjetQCM/collaborateur/addCandidat" method="post">
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="nom">Nom </Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="text" name="nom" value="" required>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="prenom">Prenom </Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="text" name="prenom" value="" required>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="email">Email </Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="email" name="email" required>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="password">Mot de passe</Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="text" name="password" required>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="candidat">Candidat</Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="radio" name="role" id="candidat" value="candidat"
								checked>
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label for="collaborateur">Collaborateur</Label>
						</div>
						<div class="col-12 col-sm-10">
							<input type="radio" name="role" id="collaborateur"
								value="collaborateur">
						</div>
					</div>
					<div class="row">
						<div class="col-12 col-sm-2">
							<Label>Promotion</Label>
						</div>
						<div class="col-12 col-sm-10">
							<select name="idPromotion" required>
								<option selected disabled hidden>Choisir une promotion</option>
								<%
									for (Promotion p : listePromotion) {
								%>
								<option value="<%=p.getCodePromo()%>"><%=p.getLibelle()%></option>
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
		<div class="col-xs-12 col-sm-3"></div>
	</div>
</body>
</html>