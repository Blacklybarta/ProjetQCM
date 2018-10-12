<% if (String.valueOf(session.getAttribute("candidat")).equals("true")) { %>
	<%@include file="../fragments/menuCandidat.html" %>
<% } else { %>
	<%@include file="../fragments/menuCollab.html" %>
<% } %>