<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="bo.Test"%>
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
<title>Gestion - Créer une section</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<%
			List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
			List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
		%>
		<form action="/ProjetQCM/collaborateur/addSection" method="post">
			<Label for="nbQuestions">Nombre de quesions à tirer pour la section</Label>
			<input type="number" name="nbQuestions" step="1" min="0" value="1" required><br>
			<label>Thème de la section : </label> 
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
			<label>Test de la section : </label> 
			<select name="idTest" required>
				<option selected disabled hidden>Choisir un test</option>
				<%
					for (Test t : listeTest) {
				%>
				<option value="<%=t.getIdTest()%>"><%=t.getLibelle()%></option>
				<%
					}
				%>
			</select><br>
			<button type="submit">Créer la section</button>
		</form>
	</div>
	<div class="col-xs-12 col-sm-3">
		<button>
			<a href="<%=request.getContextPath()%>/collaborateur/gestion.jsp">Retour</a>
		</button>
	</div>
</body>
</html>