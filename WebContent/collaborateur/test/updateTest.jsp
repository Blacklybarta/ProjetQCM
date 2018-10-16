<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Test"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Modification d'un test</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<h2>Modification d'un test</h2>
			<%
				Test test = (Test) request.getAttribute("test");
				List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
			%>
			<%
				if (test == null) {
			%>
			<p>Sélectionnez le test que vous souhaitez modifier.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updateTest" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idTest" required>
							<option selected disabled hidden>Choisir un test</option>
							<%
								for (Test u : listeTest) {
							%>
							<option value="<%=u.getIdTest()%>"><%=u.getLibelle()%></option>
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
			<p>Renseignez les informations du test.</p>
			<form class="updateTheme"
				action="/ProjetQCM/collaborateur/updateTest" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="libelle">Nom du test</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="libelle" value="<%=test.getLibelle()%>" required>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="description">Description</Label>
					</div>
					<div class="col-12 col-sm-10">
						<textarea name="description" cols="30" rows="4"
							required><%=test.getDescription()%></textarea>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="duree">Durée du test (minutes)</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="duree" step="10" min="30" value="30" value="<%=test.getDuree()%>"
							required>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="seuilHaut">Seuil haut du test (en %)</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="seuilHaut" step="1" min="0" value="<%=test.getSeuilHaut()%>"
							max="100" required>
					</div>
				</div>

				<div class="row">
					<div class="col-12 col-sm-2">
						<Label for="seuilBas">Seuil bas du test (en %)</Label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="number" name="seuilBas" step="1" min="0" value="<%=test.getSeuilBas()%>"
							max="100" required>
					</div>
				</div>
				<input type="hidden" name="update" value="true" />
				<input type="hidden" name="id" value="<%=test.getIdTest()%>" />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/collaborateur/updateTest"><button
								class="btn btn-secondary" type="button">Retour</button></a>
						<button class="btn btn-primary" type="submit">Modifier</button>
					</div>
				</div>
			</form>
			<%
				}
			%>
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>