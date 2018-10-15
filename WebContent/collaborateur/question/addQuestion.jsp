<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Création d'une question</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<h4>Création d'une nouvelle question</h4>
			<%
				List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
			%>
			<form action="/ProjetQCM/collaborateur/addQuestion" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="enonce">Enonce</label>
					</div>
					<div class="col-12 col-sm-10">
						<textarea rows="3" cols="30" placeholder="Enoncé de la question ici" name="enonce" required></textarea>
					</div>
				</div>
			
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="points">Points</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" step="1" min="1" value="1" name="points" required>
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label>Thème de la question</label>
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
						<label for="proposition1">Proposition 1</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition1" required>
						Réponse <input type="checkbox" name="chkProposition1">
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="proposition2">Proposition 2</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition2" required>
						Réponse <input type="checkbox" name="chkProposition2">
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="proposition3">Proposition 3</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition3" required>
						Réponse <input type="checkbox" name="chkProposition3">
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="proposition4">Proposition 4</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition4" required>
						Réponse <input type="checkbox" name="chkProposition4">
					</div>
				</div>
				
				<a href="<%=request.getContextPath()%>/validerAcces"><button class="btn btn-secondary" type="button">Annuler</button></a>
				<button class="btn btn-primary" type="submit">Créer la question</button>
			</form>
		</div>
	</div>
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>