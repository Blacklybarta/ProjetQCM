<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Modification d'un thème</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>
		
		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="row">
			<div class="col-12">
				<% Theme theme = (Theme) request.getAttribute("theme"); %>
				<% List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes"); %>
				<h2>Modification d'un thème</h2>
					
				<% if (theme == null) { %>
					<p>Sélectionnez le thème que vous souhaitez modifier.</p>
					<form class="updateUser" action="/ProjetQCM/collaborateur/updateTheme" method="post">
						<div class="row">
							<div class="col-12 col-sm-2">
								<select name="idTheme" required>
									<option selected disabled hidden>Choisir un thème</option>
									<% for (Theme u : listeTheme) { %>
										<option value="<%=u.getIdTheme()%>"><%=u.getLibelle()%></option>
									<% } %>
								</select> <input type="hidden" name="select" value="true" />
							</div>
						</div>
						<br/>
						<div class="row">
							<div class="col-12">
								<a href="<%=request.getContextPath()%>/collaborateur/gestion.jsp"><button class="btn btn-secondary" type="button">Retour</button></a>
								<button class="btn btn-primary" type="submit">Sélectionner</button>
							</div>
						</div>
					</form>
				<% } else { %>
					<p>Renseignez le nouveau nom du thème.</p>
					<form class="updateTheme" action="/ProjetQCM/collaborateur/updateTheme" method="post">
						<div class="row">
							<div class="col-12 col-sm-2">
								<label for="libelle">Libellé</label>
							</div>
							<div class="col-12 col-sm-10">
								<input type="text" name="libelle" value="<%=theme.getLibelle()%>" />
							</div>
						</div>
							<input type="hidden" name="update" value="true" />
							<input type="hidden" name="id" value="<%=theme.getIdTheme()%>" />
						<div class="row">
							<div class="col-12">
								<a href="<%=request.getContextPath()%>/collaborateur/gestion.jsp"><button class="btn btn-secondary" type="button">Annuler</button></a>
								<button class="btn btn-primary" type="submit">Modifier</button>
							</div>
						</div>
					</form>
				<% } %>
			</div>
		</div>
	</div>
	
	<%@include file="../../fragments/script.jsp"%>
</body>
</html>