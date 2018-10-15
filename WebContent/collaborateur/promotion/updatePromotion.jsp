<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Promotion"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Modification d'une promotion</title>
<%
	List<Promotion> listePromotion = (ArrayList<Promotion>) request.getAttribute("listePromotions");
	Promotion promotion = (Promotion) request.getAttribute("promotion");
%>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<h2>Modification d'une promotion</h2>
			<%
				if (promotion == null) {
			%>
			<p>Sélectionnez la promotion que vous souhaitez modifier.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/updatePromotion" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<select name="idPromotion" required>
							<option selected disabled hidden>Choisir une promotion</option>
							<%
								for (Promotion u : listePromotion) {
							%>
							<option value="<%=u.getCodePromo()%>"><%=u.getLibelle()%></option>
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
			<p>Renseignez le nouveau nom de la promotion</p>
			<form class="updateTheme"
				action="/ProjetQCM/collaborateur/updatePromotion" method="post">
				<div class="row">
					<div class="col-12 col-sm-2">
						<label for="libelle">Libellé</label>
					</div>
					<div class="col-12 col-sm-10">
						<input type="text" name="libelle" value="<%=promotion.getLibelle()%>" />
					</div>
				</div>
				<input type="hidden" name="update" value="true" /> <input
					type="hidden" name="id" value="<%=promotion.getCodePromo()%>" />
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/collaborateur/updatePromotion"><button
								class="btn btn-secondary" type="button">Annuler</button></a>
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