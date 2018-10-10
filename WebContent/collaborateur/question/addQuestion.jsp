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
<title>Gestion - Cr�er une question</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<h4>Cr�ation d'une nouvelle question</h4>
		<div class="contenuAccueil">
			<form action="/ProjetQCM/collaborateur/addQuestion" method="post">
				<Label for="enonce">Enonce :</Label> 
				<textarea rows="3" cols="30" placeholder="Enonc� de la question ici" name="enonce" />
				<button type="submit">Cr�er la question</button>
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