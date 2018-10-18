<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page import="bo.Epreuve" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../fragments/head.jsp"%>
<title>QCM - Passage épreuve</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../../fragments/header.jsp"%>

		<div class="row">
		<div class="col-12 col-sm-8">
			<h2>Récapitulatif des questions</h2>
			
			<div class="col-12 col-sm-4">
			<p>Questions :</p>
			<c:forEach begin="1" end="${fn:length(allQuestions)}" varStatus="loop">
				<form class="boutonQuestion" action="../question/${loop.index}" method="POST">
					<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />
					<input type="hidden" name="idQuestion"
						value="${questionTirage.question.idQuestion}" />
					<c:choose>
						<c:when test="${allQuestions[loop.index-1].estMarquee == true}">
							<button class="btn btn-warning" type="submit" name="idQuestion"
								value="${questionTirage.question.idQuestion}">${loop.index}</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-primary" type="submit">${loop.index}</button>
						</c:otherwise>
					</c:choose>
				</form>
			</c:forEach>
			
			<form action="../question/terminer" method="POST">
				<input type="hidden" name="idQuestion" value="${questionTirage.question.idQuestion}" />
				<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />
				<button class="btn btn-primary" type="submit">Terminer Test</button>
			</form>
		</div>
		</div>
	</div>
</body>
</html>