<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Gestion - Créer une question</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<h4>Création d'une nouvelle question</h4>
		<div class="contenuAccueil">
			<%
				List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
			%>
			<form action="/ProjetQCM/collaborateur/addQuestion" method="post">
				<Label for="enonce">Enonce :</Label> 
				<textarea rows="3" cols="30" placeholder="Enoncé de la question ici" name="enonce" required></textarea><br>
				<Label for="points">Points :</Label> 
				<input type="number" step="1" min="1" value="1" name="points" required><br>
				<label>Thème de la question : </label>
				<select name="idTheme" required>
					<option selected disabled hidden>Choisir un thème</option>
					<%
						for (Theme u : listeTheme) {
					%>
					<option value="<%=u.getIdTheme()%>"><%=u.getLibelle()%></option>
					<%
						}
					%>
				</select><br>
				<Label for="proposition1">Proposition 1 :</Label> 
				<input type="text" name="proposition1" required>
				Réponse :
				<input type="checkbox" name="chkProposition1"><br>
				
				<Label for="proposition2">Proposition 2 :</Label> 
				<input type="text" name="proposition2" required>
				Réponse :
				<input type="checkbox" name="chkProposition2"><br>
				
				<Label for="proposition3">Proposition 3 :</Label> 
				<input type="text" name="proposition3" required>
				Réponse :
				<input type="checkbox" name="chkProposition3"><br>
				
				<Label for="proposition4">Proposition 4 :</Label> 
				<input type="text" name="proposition4" required>
				Réponse :
				<input type="checkbox" name="chkProposition4"><br>				
				
				<button type="submit">Créer la question</button>
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