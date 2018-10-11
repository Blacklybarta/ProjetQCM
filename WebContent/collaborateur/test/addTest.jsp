<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Gestion - Créer un test</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<h4>Création d'un nouveau test</h4>
		<div class="contenuAccueil">
			<form action="/ProjetQCM/collaborateur/addTest" method="post">
				<Label for="libelle">Nom du test</Label> 
				<input type="text" name="libelle" value="" required><br>
				
				<Label for="description">Description</Label> 
				<textarea name="description" cols="30" rows="4" placeholder="Description du test" required></textarea><br>
				
				<Label for="duree">Durée du test en minutes</Label> 
				<input type="number" name="duree" step="10" min="30" value="30" required><br>
				
				<Label for="seuilHaut">Seuil haut du test (en %)</Label> 
				<input type="number" name="seuilHaut" step="1" min="0" value="1" max="100"  required><br>
				
				<Label for="seuilBas">Seuil bas du test (en %)</Label> 
				<input type="number" name="seuilBas" step="1" min="0" value="1" max="100"  required><br>
				
				<button type="submit">Créer le test</button>
			</form>
		</div>
	</div>
	<div class="col-xs-12 col-sm-3">
		<button>
			<a href="<%=request.getContextPath()%>/collaborateur/gestion.jsp">Retour</a>
		</button>
	</div>
</body>
</html>