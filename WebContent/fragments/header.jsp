<header>
	<a href="<%=request.getContextPath()%>/validerAcces">
		<img id="logo" alt="logo" src="/ProjetQCM//media/logoENI.jpg" />
	</a>
	<h1 id="titre">Plateforme de test de l'ENI</h1>
	<% if (session.getAttribute("nomUtilisateur") != null) { %>
		<p class="connecte">Connecté en tant que <strong><%= session.getAttribute("nomUtilisateur") %></strong></p>
	<% } %>
</header>