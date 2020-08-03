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
				action="${pageContext.request.contextPath}/register">
				<p>
					<label class="label-vte-art" for="article">Article*: </label> <input
						class="input-vte-art" type="text" name="article" id="article"
						value="${article}">
				</p>
				<p>
					<label class="label-vte-art" for="description">Description*:
					</label>
					<textarea class="input-vte-art" rows="3" cols="20"
						name="description" id="description" value="${description}"></textarea>
				</p>
				<p>
					<label class="label-vte-art" for="categorie">Catégories*: </label>
					<select name="categorie" id="categorie">
						<option value="">Toutes</option>
						<option value="informatique">Informatique</option>
						<option value="ameublement">Ameublement</option>
						<option value="vetement">Vêtement</option>
						<option value="sport">Sport et loisirs</option>
					</select>
				</p>
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