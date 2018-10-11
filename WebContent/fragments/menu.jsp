<% if (String.valueOf(session.getAttribute("candidat")).equals("true")) { %>
	<%@include file="../fragments/menuCandidat.html" %>
<% } %>
	
<% if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) { %>
	<%@include file="../fragments/menuCollab.html" %>
<% } %>