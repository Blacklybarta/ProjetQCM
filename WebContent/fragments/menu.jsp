<div class="menu">
<h3>Menu</h3>
<% if (String.valueOf(session.getAttribute("candidat")).equals("true")) { %>
<%@include file="../fragments/menuCandidat.html" %>
<hr>
<% } %>
<% if (String.valueOf(session.getAttribute("collaborateur")).equals("true")) { %>
<%@include file="../fragments/menuCollab.html" %>

<% } %>
</br>
<a href="/ProjetQCM/logout">Déconnexion</a></br>
<% if (session.getAttribute("nomUtilisateur") != null) { %>
	<p class="connecte">Connecté en tant que <%= session.getAttribute("nomUtilisateur") %></p>
<% } %>
</div>