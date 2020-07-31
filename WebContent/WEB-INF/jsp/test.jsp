<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<h1>Test</h1>
	<%
		boolean connected = false;
		String pseudo = "";
		if (session.getAttribute("connected")!=null) {
			connected = (boolean)session.getAttribute("connected");
			pseudo = (String)session.getAttribute("pseudo");
		}
	
		if (connected) {

	%>	
			<p>Bonjour <%=pseudo %> ! Vous êtes connecté !</p>
	
	<%
		} else {
			
			%>
				Vous n'êtes pas connecté !
			<%
		}
	 %>
</body>
</html>