<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page import="bo.Epreuve" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Question n°${questionTirage.numOrdre}</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<div class="contenu">
			<h3>Question n°${questionTirage.numOrdre}</h3>
		
			<h2>${questionTirage.question.enonce}</h2>
			
			<c:choose>
				<c:when test="${nbQuestionsTotal == questionTirage.numOrdre}">
					<form action="../question/terminer" method="POST">
				</c:when>
				<c:otherwise>
					<form action="../question/${questionTirage.numOrdre + 1}" method="POST">
				</c:otherwise>
			</c:choose>
			
				<c:forEach items="${questionTirage.question.listeProposition}" var="proposition">
					<c:set var = "chebx" value = "${0}"/>
					<c:forEach items="${answers}" var="reponse">
						<c:set var="idProp">${proposition.idProposition}</c:set>
						<c:if test="${reponse.contains(idProp) && reponse[0] eq questionTirage.question.idQuestion}">
							<input type="checkbox" value="${proposition.idProposition}" name="proposition" id="prop${proposition.idProposition}" checked>
					<c:set var = "chebx" value = "${1}"/>
						</c:if>
					</c:forEach>
						<c:if test="${chebx == 0}">
								<input type="checkbox" value="${proposition.idProposition}" name="proposition" id="prop${proposition.idProposition}">
						</c:if>
					<label for="prop${proposition.idProposition}">${proposition.enonce}</label> 
				</c:forEach> 

				<input type="hidden" name="idQuestion" value="${questionTirage.question.idQuestion}" />
				<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />
					
				<c:choose>
					<c:when test="${nbQuestionsTotal == questionTirage.numOrdre}">
						<button type="submit">Terminer Test</button>
					</c:when>
					<c:otherwise>
						<button type="submit">Question Suivante</button>
						
						<c:choose>
							<c:when test="${questionTirage.estMarquee == false}">
								<button type="submit" name="mark" value="true">Marquer la question</button>
							</c:when>
							<c:otherwise>
								<button type="submit" name="mark" value="false">Démarquer la question</button>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</form>
				
			<p>Questions :</p>
			<c:forEach begin="1" end="${nbQuestionsTotal}" varStatus="loop">
				<form action="../question/${loop.index}" method="POST">
					<input type="hidden" name="idEpreuve" value="${epreuve.idEpreuve}" />
					<input type="hidden"  name="idQuestion" value="${questionTirage.question.idQuestion}" />
					<c:choose>
						<c:when test="${allQuestions[loop.index-1].estMarquee == true}">
							<button type="submit" name="idQuestion" value="${questionTirage.question.idQuestion}">${loop.index}*</button>
						</c:when>
						<c:otherwise>
							<button type="submit">${loop.index}</button>
						</c:otherwise>
					</c:choose>
				</form>
			</c:forEach>
		</div>
	</div>
</body>
</html>