<%@page import="fr.eni.javaee.eniencheres.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>

	<nav class="navbar navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/encheres">ENI-Enchères</a>
		</div>
	</nav>

	<h1 style="text-align: center; padding: 10px;">Mon profil</h1>

	<c:if test="${!empty listeCodesErreur}">
		<div class="block-msg-err">
			<p style="color: red;">Erreur(s) :</p>
			<ul>
				<c:forEach var="code" items="${listeCodesErreur}">
					<li>${LecteurMessage.getMessageErreur(code)}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>

	<div class="block-inscription">
		<form method="post"
			action="${pageContext.request.contextPath}/register">
			<p>
				<label class="label-inscription" for="pseudo">Pseudo*: </label> <input
					class="input-inscription" type="text" name="pseudo" id="pseudo"
					value="${pseudo}"> <label class="label-inscription"
					for="nom">Nom*: </label> <input type="text" name="nom" id="nom"
					value=${nom}>
			</p>
			<p>
				<label class="label-inscription" for="prenom">Prénom*: </label> <input
					class="input-inscription" type="text" name="prenom" id="prenom"
					value="${prenom}"> <label class="label-inscription"
					for="email">Email*: </label> <input type="email" name="email"
					id="email" value="${email}">
			</p>
			<p>
				<label class="label-inscription" for="telephone">Téléphone:
				</label> <input class="input-inscription" type="text" name="telephone"
					id="telephone" value="${telephone}"> <label
					class="label-inscription" for="rue">Rue*: </label> <input
					type="text" name="rue" id="rue" value="${rue}">
			</p>
			<p>
				<label class="label-inscription" for="cp">Code postal*: </label> <input
					class="input-inscription" type="text" name="cp" id="cp"
					value="${cp}"> <label class="label-inscription" for="ville">Ville*:
				</label> <input type="text" name="ville" id="ville" value="${ville }">
			</p>
			<p>
				<label class="label-inscription" for="password">Mot de
					passe*: </label> <input class="input-inscription" type="password"
					name="password" id="password"> <label
					class="label-inscription" for="confirm">Confirmation*: </label> <input
					type="password" name="confirm" id="confirm">
			</p>
			<div class="block-btn-inscription">
				<input class="btn-inscription" type="submit" value="Créer">
				<a href="${pageContext.request.contextPath}/encheres"
					class="btn btn-secondary btn-lg active" id="btn-inscription-reset"
					role="button" aria-pressed="true">Annuler</a>
			</div>
		</form>
	</div>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>