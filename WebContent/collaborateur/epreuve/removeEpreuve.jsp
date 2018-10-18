<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Epreuve"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Suppression d'une épreuve</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<%
				List<Epreuve> listeEpreuve = (ArrayList<Epreuve>) request.getAttribute("listeEpreuves");
			%>
			<h2>Suppression d'une épreuve</h2>
			<p>Sélectionnez l'épreuve que vous souhaitez supprimer.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/removeEpreuve" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idEpreuve" required>
							<option selected disabled hidden>Choisir une épreuve</option>
							<%
								for (Epreuve u : listeEpreuve) {
							%>
							<option value="<%=u.getIdEpreuve()%>"><%=u.getTest().getLibelle() + " - " + u.getDateDebutValidite()%></option>
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