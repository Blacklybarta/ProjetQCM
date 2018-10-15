<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Création d'une promotion</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>
		
		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<h2>Création d'une promotion</h2>
			<p>Rentrer le nom de la promotion que vous souhaitez créer.</p>

			<form action="/ProjetQCM/collaborateur/addPromotion" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="nom">Nom</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="nom" value="" required>
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
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>