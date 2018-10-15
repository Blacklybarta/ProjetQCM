<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Ajout d'un test</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-xs-12 col-sm-9">
			<h4>Création d'un nouveau test</h4>
			<form action="/ProjetQCM/collaborateur/addTest" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="libelle">Nom du test</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="libelle" value="" required>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="description">Description</Label>
					</div>
					<div class="col-12 col-sm-10">
						<textarea name="description" cols="30" rows="4"
							placeholder="Description du test" required></textarea>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="duree">Durée du test (minutes)</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="duree" step="10" min="30" value="30"
							required>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="seuilHaut">Seuil haut du test (en %)</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="seuilHaut" step="1" min="0" value="1"
							max="100" required>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="seuilBas">Seuil bas du test (en %)</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="seuilBas" step="1" min="0" value="1"
							max="100" required>
					</div>
				</div>

				<a href="<%=request.getContextPath()%>/collaborateur/gestion.jsp"><button
						class="btn btn-secondary" type="button">Annuler</button></a>
				<button class="btn btn-primary" type="submit">Créer le test</button>
			</form>
		</div>
	</div>
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>