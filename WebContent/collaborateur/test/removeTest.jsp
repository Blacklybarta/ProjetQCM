<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Test"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Suppression d'un test</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<%
				List<Test> listeTest = (ArrayList<Test>) request.getAttribute("listeTests");
			%>
			<h2>Suppression d'un test</h2>
			<p>Sélectionnez le test que vous souhaitez supprimer.</p>
			<form class="updateUser" action="/ProjetQCM/collaborateur/removeTest"
				method="post">
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
						</select>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/validerAcces"><button
								class="btn btn-secondary" type="button">Retour</button></a>
						<button class="btn btn-primary" type="submit">Supprimer</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>