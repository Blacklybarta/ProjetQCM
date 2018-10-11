<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../fragments/head.jsp"%>
	<title>QCM - Connexion</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../fragments/header.jsp"%>

		<div class="row">
			<div class="col-12">
				<h2>Connexion</h2>
				<p>Merci de rentrer vos identifiants.</p>

				<!-- Formulaire de connexion -->
				<div class="contenuAccueil">
					<form action="/ProjetQCM/validerAcces" method="post">
						<div class="row">
							<div class="col-12 col-sm-2">
								<label for="email">EMAIL</label>
							</div>
							<div class="col-12 col-sm-10">
								<input type="text" name="email" value="" required><br>
							</div>
						</div>

						<div class="row">
							<div class="col-12 col-sm-2">
								<label for="password">MOT DE PASSE</label>
							</div>
							<div class="col-12 col-sm-10">
								<input type="password" name="password" value="" required><br>
							</div>
						</div>

						<div class="row">
							<div class="col-12 col-sm-2">
								<button class="btn btn-primary" type="submit">SE
									CONNECTER</button>
							</div>
							<div class="col-12 col-sm-10"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../fragments/script.jsp"%>
</body>
</html>