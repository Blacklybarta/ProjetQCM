<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Utilisateur"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Suppresion d'un candidat</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<%
				List<Utilisateur> listeUtilisateur = (ArrayList<Utilisateur>) request.getAttribute("listeUtilisateurs");
			%>
			<h2>Suppresion d'un utilisateur</h2>
			<p>Sélectionnez l'utilisateur que vous souhaitez supprimer.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/removeCandidat" method="post">
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
						</select>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/validerAcces"><button
								class="btn btn-secondary" type="button">Retour</button></a>
						<button class="btn btn-primary" type="submit">Supprimer</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>