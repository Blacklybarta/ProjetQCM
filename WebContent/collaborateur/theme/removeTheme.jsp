<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Theme"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Supprimer un thème</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="row">
			<div class="col-12">
				<div class="contenu">
					<%
						List<Theme> listeTheme = (ArrayList<Theme>) request.getAttribute("listeThemes");
					%>
					<h2>Supprimer un thème</h2>
					<p>Sélectionnez le thème que vous souhaitez supprimer.</p>
					<form class="updateUser"
						action="/ProjetQCM/collaborateur/removeTheme" method="post">
						<div class="row">
							<div class="col-12 col-sm-2">
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
		</div>
	</div>

	<%@include file="../../fragments/script.jsp"%>
</body>
</html>