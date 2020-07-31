<%@page import="fr.eni.javaee.eniencheres.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>

<body>

	<nav class="navbar navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="#">ENI-Enchères</a>
		</div>
	</nav>
	
	<c:if test="${!empty listeCodesErreur}">
			<div class="block-msg-err">
			  <p style="color:red;">Echec de la connexion :</p>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
	
	<div class="block-connexion">
	<form method="post" action="${pageContext.request.contextPath}/login">
		<p><label class="label-connexion" for="pseudo">Identifiant: </label>
		<input type="text" name="pseudo" id="pseudo"></p>
		
		<p><label class="label-connexion" for="password">Mot de passe: </label>
		<input type="password" name="password" id="password"></p>
		
		<div class="block-btn-connexion">
			<input class="btn-connexion" type="submit" value="Connexion">
			<div class="block-remember">
				<p><input type="checkbox" name="rememberme" id="remembercheckbox" /><label for="remembercheckbox">Se souvenir de moi</label></p>
				<a href="#">Mot de passe oublié</a>
			</div>
		</div>
	</form>
	</div>
	
	<div class="block-btn-creation">
		<a class="btn btn-secondary btn-lg active" id="btn-creation" href="${pageContext.request.contextPath}/register" role="button">Créer un compte</a>
	</div>
	
	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>