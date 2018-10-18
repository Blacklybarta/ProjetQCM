<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bo.Question"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Suppression d'une question</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp"%>

		<div class="col-12">
			<%
				List<Question> listeQuestion = (ArrayList<Question>) request.getAttribute("listeQuestions");
			%>
			<h2>Suppression d'une question</h2>
			<p>Sélectionnez la question que vous souhaitez supprimer.</p>
			<form class="updateUser"
				action="/ProjetQCM/collaborateur/removeQuestion" method="post">
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