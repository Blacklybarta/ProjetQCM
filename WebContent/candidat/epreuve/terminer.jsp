<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page import="bo.Epreuve" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>R�sultats</title>
</head>
<body>
	<div class="col-xs-12 col-sm-9">
		<div class="contenu">
			<h3>R�sultat de l'�preuve ${epreuve.test.libelle}</h3>
		
			<h2>La note re�ue est ${note}</h2>
			
		</div>
	</div>
	
	<div class="col-xs-12 col-sm-3">
		<!-- Menu -->
		<%@include file="../../fragments/menu.jsp" %>
	</div>
</body>
</html>