<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page import="bo.Epreuve" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="../fragments/head.jsp"%>
	<title>QCM - Accueil candidat</title>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@include file="../fragments/header.jsp"%>
		
		<!-- Menu -->
		<%@include file="../fragments/menu.jsp"%>

		<div class="col-xs-12 col-sm-9">
			<h2>Accueil candidat</h2>
			En tant que candidats vous pouvez :
			<ul>
				<li>Tests</li>
				<c:if test="${empty myObject.featuresList}">
					<c:forEach items="${epreuves}" var="epreuve">
						<c:if test="${epreuve.etat == 'EA'}">
							<li class="sousUL">
								<ul>
									<li>
										<p>Nom : ${epreuve.test.libelle},</p>
										<p>description : ${epreuve.test.description},</p>
										<p>Date Fin : ${epreuve.dateFinValidite}</p>
	
										<form action="epreuve" method="POST">
											<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />
											<button class="btn btn-primary" type="submit">Passer cette épreuve</button>
										</form>
									</li>
								</ul>
							</li>
						</c:if>
						<c:if test="${epreuve.etat == 'EC'}">
							<li class="sousUL">
								<ul>
									<li>
										<p>Nom : ${epreuve.test.libelle},</p>
										<p>description : ${epreuve.test.description},</p>
										<p>Date Fin : ${epreuve.dateFinValidite}</p>
										
										<form action="epreuve" method="POST">
											<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />
											<button class="btn btn-primary" type="submit">Continuer cette épreuve</button>
										</form>
									</li>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</c:if>
				<li>Voir l'historique de vos tests</li>
				<c:if test="${empty myObject.featuresList}">
					<c:forEach items="${epreuves}" var="epreuve">
						<c:if test="${epreuve.etat == 'T'}">
							<li class="sousUL">
								<ul>
									<li>
										${epreuve.test.libelle}
									</li>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
	
	<%@include file="../fragments/script.jsp"%>
</body>
</html>