<div id="menu">
	<h3>Menu</h3>
	<% if (String.valueOf(session.getAttribute("candidat")).equals("true")) { %>
	<%@include file="../fragments/menuCandidat.html" %>
	<% } %>
	
	<% if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) { %>
	<%@include file="../fragments/menuCollab.html" %>
	<% } %>
	
	</br>
	<% if (session.getAttribute("nomUtilisateur") != null) { %>
		Connecté en tant que <%= session.getAttribute("nomUtilisateur") %>
	<% } %>
	<a href="/ProjetQCM/logout">Déconnexion</a></br>
</div>