<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Utilisateur"%>
<%@ page import="bo.Promotion"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Modification d'un candidat</title>

</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<%
				Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
				List<Utilisateur> listeUtilisateur = (ArrayList<Utilisateur>) request.getAttribute("listeUtilisateurs");
				List<Promotion> listePromotion = (ArrayList<Promotion>) request.getAttribute("listePromotions");
			%>
			<h2>Modification d'un utilisateur</h2>
			<%
				if (utilisateur == null) {
			%>
			<p>Sélectionnez l'utilisateur que vous souhaitez modifier.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updateCandidat" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idUser" required>
							<option selected disabled hidden>Choisir un utilisateur</option>
							<%
								for (Utilisateur u : listeUtilisateur) {
							%>
							<option value="<%=u.getIdUtilistaeur()%>"><%=u.getNom() + " " + u.getPrenom()%></option>
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
			<p>Renseignez les informations de l'utilisateur.</p>
			<form class="updateTheme"
				action="/ProjetQCM/collaborateur/updateCandidat" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="nom">Nom </Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="nom" value="<%=utilisateur.getNom()%>" required>
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="prenom">Prenom </Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="prenom" value="<%=utilisateur.getPrenom()%>" required>
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="email">Email </Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="email" name="email" value="<%=utilisateur.getEmail()%>" required>
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="password">Mot de passe</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="password" value="<%=utilisateur.getPassword()%>" required>
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
				<input type="hidden" name="update" value="true" />
				<input type="hidden" name="id" value="<%=utilisateur.getIdUtilistaeur()%>" />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/collaborateur/updateCandidat"><button
								class="btn btn-secondary" type="button">Retour</button></a>
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