<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Ajout d'un thème</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>
		
		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="row">
			<div class="col-12">
				<h2>Création d'un thème</h2>
				<p>Rentrer le nom du thème que vous souhaitez créer.</p>
				<div class="contenuAccueil">
					<form action="/ProjetQCM/collaborateur/addTheme" method="post">
						<div class="row">
							<div class="col-12 col-sm-2">
								<Label for="libelle">Nom du thème</Label>
							</div>
							<div class="col-12 col-sm-10">
								<input type="text" name="libelle" value="" required>
							</div>
						</div>
						
						<div class="row">
							<div class="col-12">
								<a href="<%=request.getContextPath()%>/validerAcces"><button class="btn btn-secondary" type="button">Annuler</button></a>
								<button class="btn btn-primary" type="submit">Créer</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>