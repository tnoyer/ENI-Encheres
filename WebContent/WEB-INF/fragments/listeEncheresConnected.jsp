<%@page import="fr.eni.javaee.eniencheres.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="#">ENI-Enchères</a>
		<div class="block-nav-link">
			<a class="nav-link"
				href="${pageContext.request.contextPath}/encheres">Enchères</a> <a
				class="nav-link"
				href="${pageContext.request.contextPath}/nouvelleVente">Vendre
				un article</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/profil?idUser=${id}">Mon
				profil</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/logout">Déconnexion</a>
		</div>
	</div>
</nav>

<h1 style="text-align: center; padding: 10px;">Liste des enchères</h1>

<div class="block-content">
	<h2>Filtres :</h2>

	<form action="${pageContext.request.contextPath}/encheres"
		method="post" class="block-filter-art">
		<div class="block-art-cat">
			<p>
				<input class="input-filter-art" type="text" name="articleFiltre"
					value="${articleFiltre}" placeholder="Nom de l'article..." />
			</p>
			<p class="p-cat">
				<label class="label-categorie" for="categorie">Catégories: </label>
				<select name="categorie" id="categorie">
					<option value="0">Toutes</option>
					<option value="1">Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">Vêtement</option>
					<option value="4">Sport et loisirs</option>
				</select>
			</p>

			<div class="block-radio-vte-ach">
				<div class="inner-radio">
					<input type="radio" id="achat" name="typeList" value="achat"
						checked> <label for="achat">Achats</label>
					<p>
						<input type="checkbox" id="encheresOuvertes"
							name="encheresOuvertes"> <label for="encheresOuvertes">Enchères
							ouvertes</label>
					</p>
					<p>
						<input type="checkbox" id="encheresEnCours" name="encheresEnCours">
						<label for="encheresEnCours">Mes enchères en cours</label>
					</p>
					<p>
						<input type="checkbox" id="encheresRemportees"
							name="encheresRemportees"> <label
							for="encheresRemportees">Mes enchères remportées</label>
					</p>
				</div>

				<div class="inner-radio">
					<input type="radio" id="vente" name="typeList" value="vente">
					<label for="vente">Mes ventes</label>
					<p>
						<input type="checkbox" id="ventesEnCours" name="ventesEnCours">
						<label for="ventesEnCours">Mes ventes en cours</label>
					</p>
					<p>
						<input type="checkbox" id="ventesNonDébutées"
							name="ventesNonDébutées"> <label for="ventesNonDébutées">Mes
							ventes non débutées</label>
					</p>
					<p>
						<input type="checkbox" id="ventesTerminees" name="ventesTerminees">
						<label for="ventesTerminees">Ventes terminées</label>
					</p>
				</div>
			</div>

		</div>
		<div>
			<input class="btn-submit-filter-art" type="submit" value="Rechercher" />
		</div>
	</form>

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

	<c:choose>
		<c:when test="${listeArticles.size()>0}">
			<div class="block-liste-encheres">
				<c:forEach var="a" items="${listeArticles}">
					<div class="card" id="art-enchere">
						<h5 class="card-header">${a.nom}</h5>
						<div class="card-body">
							<p class="card-text">Prix : ${a.prixVente }</p>
							<p class="card-text">Fin de l'enchère : ${a.dateFin }</p>
							<p class="card-text">Vendeur : <a href="${pageContext.request.contextPath}/profil?idUser=${a.utilisateur.id}">${a.utilisateur.pseudo }</a></p>
							<a href="${pageContext.request.contextPath}/detail"
								class="btn btn-primary">Détail</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p>Pas de liste actuellement.
			<p>
		</c:otherwise>
	</c:choose>

</div>