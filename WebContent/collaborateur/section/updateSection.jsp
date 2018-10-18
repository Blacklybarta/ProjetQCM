<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Section"%>
<%@ page import="bo.Test"%>
<%@ page import="bo.Theme"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Modification d'une section</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>
		<%
			Section section = (Section) request.getAttribute("section");
			List<Section> listeSection = (ArrayList<Section>) request.getAttribute("listeSections");
			List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
			List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
		%>
		<div class="col-12">
			<h2>Modification d'une section</h2>
			<%
				if (section == null) {
			%>
			<p>Sélectionnez la section que vous souhaitez modifier.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updateSection" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idSection" required>
							<option selected disabled hidden>Choisir une section</option>
							<%
								for (Section u : listeSection) {
							%>
							<option
								value="<%=u.getTest().getIdTest() + "" + u.getTheme().getIdTheme()%>"><%="Test : " + u.getTest().getLibelle() + ", " + "Theme : " + u.getTheme().getLibelle()%></option>
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
			<p>Renseignez les informations de la section.</p>
			<form class="updateTheme"
				action="/ProjetQCM/collaborateur/updateSection" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="nbQuestions">Nombre de quesions à tirer pour
							la section</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="nbQuestions" step="1" min="0"
							value="<%=section.getNbQuestionATirer()%>" required>
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
				<input type="hidden" name="update" value="true" /> <input
					type="hidden" name="id"
					value="<%=request.getAttribute("idSection")%>" /> <a
					href="<%=request.getContextPath()%>/collaborateur/updateSection"><button
						class="btn btn-secondary" type="button">Annuler</button></a>
				<button class="btn btn-primary" type="submit">Créer la
					section</button>
			</form>
			<%
				}
			%>
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>