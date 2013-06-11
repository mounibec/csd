<%@page contentType="text/html; UTF-8" %>

<html>
<head><title>Titre</title></head>
<body>
<div><img src="images/logo.gif" alt="logo"/></div>

<jsp:include page="menu.jsp" />

<h1>Bienvenue</h1>

<p>Utilisateur : ${login}</p>

</body>
</html>
