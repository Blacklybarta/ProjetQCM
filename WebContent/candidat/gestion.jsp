<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bo.Epreuve" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Candidat - Gestion</title>
</head>
<body>
	<div class="contenu">
		<br> <br> En tant que candidats vous pouvez :<br>
		<ul>
			<li>Tests</li>
		</ul>
		<c:forEach items="${epreuves}" var="epreuve">
			${epreuve.Test.Libelle}
		</c:forEach>
		<ul>
			<li>Voir l'historique de vos tests</li>
		</ul>
	</div>
	<div >

			<!-- Menu -->
			<%@include file="../fragments/menu.jsp" %>
		</div>
</body>
</html>