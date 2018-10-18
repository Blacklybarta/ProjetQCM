<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="bo.Test"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Création d'un candidat</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>
		
		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<h2>Création d'une section</h2>
			<p>Renseignez les informations de la section à créer.</p>
		
			<%
				List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
				List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
			%>
			<form action="/ProjetQCM/collaborateur/addSection" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="nbQuestions">Nombre de questions</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="nbQuestions" step="1" min="0" value="1" required>
					</div>
				</div>
			
				<div class="row">
					<div class="col-12 col-sm-2">
						<label>Thème de la section</label>
					</div>
					<div class="col-12 col-sm-10">
						<select name="idTheme" required>
							<option selected disabled hidden>Choisir un thème</option>
							<%
								for (Theme u : listeTheme) {
							%>
							<option value="<%=u.getIdTheme()%>"><%=u.getLibelle()%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<label>Test de la section</label>
					</div>
					<div class="col-12 col-sm-10">
						<select name="idTest" required>
							<option selected disabled hidden>Choisir un test</option>
							<%
								for (Test t : listeTest) {
							%>
							<option value="<%=t.getIdTest()%>"><%=t.getLibelle()%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>
				
				<a href="<%=request.getContextPath()%>/validerAcces"><button
								class="btn btn-secondary" type="button">Retour</button></a>
				<button class="btn btn-primary" type="submit">Créer la section</button>
			</form>
		</div> 
	</div>
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>