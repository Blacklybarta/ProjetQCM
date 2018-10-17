<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page import="bo.Epreuve" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../../fragments/head.jsp"%>
	<title>QCM - Commencer épreuve</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<div class="col-12">
			<h2>Commencer l'épreuve ?</h2>
			
			<h3>${epreuve.test.libelle}</h3>
			<p>Description : ${epreuve.test.description}</p>
			<p>Durée : ${epreuve.test.duree} minutes</p>
				
			<form action="epreuve/question/start" method="POST">
				<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />			
				<button class="btn btn-primary" type="submit">Commencer l'épreuve</button>
			</form>
		</div>
	</div>
</body>
</html>