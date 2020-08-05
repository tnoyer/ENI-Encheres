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
	
	<div class="block-detail-vente">
		<div class="inner-detail-vente">
			<h2>${art.nom}</h2>
			<p>Description : ${art.description}</p>
			<p>Catégorie : ${art.categorie.libelle}</p>
			<p>Meilleure offre : ${art.prixVente } par </p>
			<p>Mise à prix : ${art.prixInitial }</p>
			<p>Fin de l'enchère : ${art.dateFin }</p>
			<p>Retrait : ${art.utilisateur.rue} ${art.utilisateur.codePostal} ${art.utilisateur.ville }</p>
			<p>Vendeur : ${art.utilisateur.pseudo }</p>
			<form action="${pageContext.request.contextPath}/detail" method="post">
				<p>
					<label class="label-proposition" for="proposition">Ma proposition : </label> 
					<input
						class="input-proposition" type="number" name="proposition" id="proposition"
						value="${art.prixVente}">
					<input class="btn-proposition" type="submit" value="Enchérir">
				</p>
			</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>