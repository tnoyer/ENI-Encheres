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

	<h1 style="text-align: center; padding: 10px;">Détail vente</h1>

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

	<div class="block-detail-vente">
		<div class="inner-detail-vente">
			<h2>${art.nom}</h2>
			<p>Description : ${art.description}</p>
			<p>Catégorie : ${art.categorie.libelle}</p>
			<c:choose>
				<c:when test="${!empty enchere}">
					<p>Meilleure offre : ${enchere.montant } par
						${utilisateur.pseudo}</p>
				</c:when>
				<c:otherwise>
					<p>Meilleure offre : Aucune offre</p>
				</c:otherwise>
			</c:choose>

			<p>Mise à prix : ${art.prixInitial }</p>
			<p>Fin de l'enchère : ${art.dateFin }</p>
			<p>Retrait : ${art.utilisateur.rue} ${art.utilisateur.codePostal}
				${art.utilisateur.ville }</p>
			<p>Vendeur : ${art.utilisateur.pseudo }</p>

			<c:if test="${id != art.utilisateur.id}">
				<form
					action="${pageContext.request.contextPath}/encherir?idArt=${art.id}"
					method="post">
					<p>
						<label class="label-proposition" for="proposition">Ma
							proposition : </label> <input class="input-proposition" type="number"
							name="proposition" id="proposition" value="${art.prixVente}">
						<input class="btn-proposition" type="submit" value="Enchérir">
					</p>
				</form>
			</c:if>

			<a href="${pageContext.request.contextPath}/encheres"
				class="btn btn-secondary btn-lg active" id="btn-detail-article-prev"
				role="button" aria-pressed="true">Retour</a>
		</div>
	</div>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>