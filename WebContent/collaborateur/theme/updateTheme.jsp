
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
<title>Gestion - Modification d'un thème</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<div class="contenu">
			<%
				Theme theme = (Theme) request.getAttribute("theme");
			%>
			<%
				List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
			%>
			<br>
			<h3>Modification d'un thème</h3>

			<!-- Choix de l'utilisateur à modifier -->
			<%
				if (theme == null) {
			%>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updateTheme" method="post">
				<select name="idTheme" required>
					<option selected disabled hidden>Choisir un thème</option>
					<%
						for (Theme u : listeTheme) {
					%>
					<option value="<%=u.getIdTheme()%>"><%=u.getLibelle()%></option>
					<%
						}
					%>
				</select> <input type="hidden" name="select" value="true" />
				<button type="submit">CHOISIR</button>
			</form>

			<!-- Modification de l'utilisateur sélectionné -->
			<%
				} else {
			%>
			<form class="updateTheme"
				action="/ProjetQCM/collaborateur/updateTheme" method="post">
				<label for="libelle">libelle</label> <input type="text"
					name="libelle" value="<%=theme.getLibelle()%>" /></br> <input
					type="hidden" name="update" value="true" /> <input type="hidden"
					name="id" value="<%=theme.getIdTheme()%>" />
				<button type="submit">MODIFIER</button>
			</form>
			<%
				}
			%>
		</div>
	</div>
	<div class="col-xs-12 col-sm-3">
		<button>
			<a href="<%=request.getContextPath()%>/collaborateur/gestion.jsp">Retour</a>
		</button>
	</div>
</body>
</html>