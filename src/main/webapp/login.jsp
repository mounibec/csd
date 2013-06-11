<html>
<head><title>Titre</title></head>
<body>
<div><img src="images/logo.gif" alt="logo"/></div>

<h1>Connection</h1>

<form action="<%= response.encodeURL("/resanet/login") %>" method="post">
    <label for="login">Identifiant</label>
    <input type="text" id="login" name="login"/> <br/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password"/> <br/>
    <input type="submit" value="Se connecter"/>
</form>
</body>
</html>
