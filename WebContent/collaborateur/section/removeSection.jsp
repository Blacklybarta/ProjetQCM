<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Section"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Suppression d'une section</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<%
				List<Section> listeSection = (ArrayList<Section>) request.getAttribute("listeSections");
			%>
			<h2>Suppression d'une section</h2>
			<p>Sélectionnez la section que vous souhaitez supprimer.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/removeSection" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idSection" required>
							<option selected disabled hidden>Choisir une section</option>
							<%
								for (Section u : listeSection) {
							%>
							<option value="<%=u.getTest().getIdTest()+""+u.getTheme().getIdTheme()%>"><%="Test : "+u.getTest().getLibelle() +
							", " + "Theme : "+u.getTheme().getLibelle()%></option>
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