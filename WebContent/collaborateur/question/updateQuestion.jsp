<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="bo.Question"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Modification d'une question</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>
		<%
			Question question = (Question) request.getAttribute("question");
			List<Question> listeQuestion= (ArrayList<Question>) request.getAttribute("listeQuestions");
			List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
		%>
		<div class="col-12">
			<h2>Modification d'une question</h2>
			<%
				if (question == null) {
			%>
			<p>Sélectionnez la question que vous souhaitez modifier.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updateQuestion" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idQuestion" required>
							<option selected disabled hidden>Choisir une question</option>
							<%
								for (Question u : listeQuestion) {
							%>
							<option value="<%=u.getIdQuestion()%>"><%=u.getEnonce()%></option>
							<%
								}
							%>
						</select> <input type="hidden" name="select" value="true" />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/validerAcces"><button
								class="btn btn-secondary" type="button">Retour</button></a>
						<button class="btn btn-primary" type="submit">Sélectionner</button>
					</div>
				</div>
			</form>
			<%
				} else {
			%>
			<p>Renseignez les informations de l'utilisateur.</p>
			<form class="updateTheme"
				action="/ProjetQCM/collaborateur/updateQuestion" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="enonce">Enonce</label>
					</div>
					<div class="col-12 col-sm-10">
						<textarea rows="3" cols="30" name="enonce" required><%= question.getEnonce() %></textarea>
					</div>
				</div>
			
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="points">Points</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" step="1" min="1" value="<%=question.getPoints()%>" name="points" required>
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
						<input type="text" name="proposition1" value="<%= question.getListeProposition().get(0).getEnonce()%>" required>
						<input type="hidden" name="idProposition1" value="<%=question.getListeProposition().get(0).getIdProposition()%>" />
						Réponse <input type="checkbox" name="chkProposition1">
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="proposition2">Proposition 2</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition2" value="<%= question.getListeProposition().get(1).getEnonce()%>" required>
						<input type="hidden" name="idProposition2" value="<%=question.getListeProposition().get(1).getIdProposition()%>" />
						Réponse <input type="checkbox" name="chkProposition2">
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="proposition3">Proposition 3</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition3" value="<%= question.getListeProposition().get(2).getEnonce()%>" required>
						<input type="hidden" name="idProposition3" value="<%=question.getListeProposition().get(2).getIdProposition()%>" />
						Réponse <input type="checkbox" name="chkProposition3">
					</div>
				</div>
				
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="proposition4">Proposition 4</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="proposition4" value="<%= question.getListeProposition().get(3).getEnonce()%>" required>
						<input type="hidden" name="idProposition4" value="<%=question.getListeProposition().get(3).getIdProposition()%>" />
						Réponse <input type="checkbox" name="chkProposition4">
					</div>
				</div>
				<input type="hidden" name="update" value="true" />
				<input type="hidden" name="id" value="<%=question.getIdQuestion()%>" />
				<a href="<%=request.getContextPath()%>/collaborateur/updateQuestion"><button class="btn btn-secondary" type="button">Annuler</button></a>
				<button class="btn btn-primary" type="submit">Modifier la question</button>
			</form>
			<%
				}
			%>
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>