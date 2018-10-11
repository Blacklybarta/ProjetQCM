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

		<div class="row">
			<div class="col-12">
				<h2>Accueil candidat</h2>
				En tant que candidats vous pouvez :
				<ul>
					<li>Tests</li>
					<c:if test="${empty myObject.featuresList}">
					<li>
						<ul>
							<c:forEach items="${epreuves}" var="epreuve">
								<li>
									${epreuve.test.libelle}
								</li>
							</c:forEach>
						</ul>
					</li>
					</c:if>
					<li>Voir l'historique de vos tests</li>
				</ul>
			</div>
		</div>
	</div>
	
	<%@include file="../fragments/script.jsp"%>
</body>
</html>