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
			<a class="navbar-brand" href="#">ENI-Enchères</a>
		</div>
	</nav>

	<h1 style="text-align: center; padding: 10px;">Nouvelle vente</h1>

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

	<div class="block-vte-art">
		<div class="img-vte-art">IMAGE</div>
		<div class="form-vte-art">
			<form method="post"
				action="${pageContext.request.contextPath}/nouvelleVente">
				<p>
					<label class="label-vte-art" for="nomArticle">Article*: </label> <input
						class="input-vte-art" type="text" name="nomArticle" id="nomArticle"
						value="${nomArticle}">
				</p>
				<p>
					<label class="label-vte-art" for="description">Description*:
					</label>
					<textarea class="input-vte-art" rows="3" cols="20"
						name="description" id="description">${description}</textarea>
				</p>
				<p>
					<label class="label-vte-art" for="categorie">Catégories*: </label>
					<select name="categorie" id="categorie">
						<option value="0">Toutes</option>
						<option value="1">Informatique</option>
						<option value="2">Ameublement</option>
						<option value="3">Vêtement</option>
						<option value="4">Sport et loisirs</option>
					</select>
				</p>
				<p>
					<label class="label-vte-art" for="photo">Photo de
						l'article: </label>

				</p>
				<p>
					<label class="label-vte-art" for="prix">Mise à prix*: </label> <input
						class="input-vte-art" type="number" name="prix" id="prix"
						value="${not empty prix ? prix : 150}">
				</p>
				<p>
					<label class="label-vte-art" for="dateDebut">Début de
						l'enchère*: </label> <input class="input-vte-art" type="date"
						name="dateDebut" id="dateDebut" value="${dateDebut}">
				</p>
				<p>
					<label class="label-vte-art" for="dateFin">Fin de
						l'enchère*: </label> <input class="input-vte-art" type="date"
						name="dateFin" id="dateFin" value="${dateFin}">
				</p>
				<fieldset>
					<legend>Retrait</legend>
					<p>
						<label class="label-vte-art" for="rue">Rue*: </label> 
						<input
							class="input-vte-art" type="text" name="rue" id="rue"
							value="${rue}">
					</p>
					<p>
						<label class="label-vte-art" for="cp">Code postal*: </label> 
						<input
							class="input-vte-art" type="text" name="cp" id="cp"
							value="${cp}">
					</p>
					<p>
						<label class="label-vte-art" for="ville">Ville*: </label> 
						<input
							class="input-vte-art" type="text" name="ville" id="ville"
							value="${ville}">
					</p>
				</fieldset>
				<div class="block-btn-vte-art">
					<input class="btn-vte-art" type="submit" value="Enregistrer">
					<a href="${pageContext.request.contextPath}/encheres"
						class="btn btn-secondary btn-lg active" id="btn-vte-art-reset"
						role="button" aria-pressed="true">Annuler</a>
				</div>
			</form>
		</div>

	</div>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>