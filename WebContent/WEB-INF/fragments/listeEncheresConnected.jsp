<%@page import="fr.eni.javaee.eniencheres.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="#">ENI-Ench�res</a>
		<div class="block-nav-link">
			<a class="nav-link"
				href="${pageContext.request.contextPath}/encheres">Ench�res</a> <a
				class="nav-link"
				href="${pageContext.request.contextPath}/nouvelleVente">Vendre
				un article</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/profil?idUser=${id}">Mon
				profil</a> <a class="nav-link"
				href="${pageContext.request.contextPath}/logout">D�connexion</a>
		</div>
	</div>
</nav>

<h1 style="text-align: center; padding: 10px;">Liste des ench�res</h1>

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
				<label class="label-categorie" for="categorie">Cat�gories: </label>
				<select name="categorie" id="categorie">
					<option value="0">Toutes</option>
					<option value="1">Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">V�tement</option>
					<option value="4">Sport et loisirs</option>
				</select>
			</p>

			<div class="block-radio-vte-ach">
				<div class="inner-radio">
					<input type="radio" id="achat" name="typeList" value="achat"
						checked> <label for="achat">Achats</label>
					<p>
						<input type="checkbox" id="encheresOuvertes"
							name="encheresOuvertes"> <label for="encheresOuvertes">Ench�res
							ouvertes</label>
					</p>
					<p>
						<input type="checkbox" id="encheresEnCours" name="encheresEnCours">
						<label for="encheresEnCours">Mes ench�res en cours</label>
					</p>
					<p>
						<input type="checkbox" id="encheresRemportees"
							name="encheresRemportees"> <label
							for="encheresRemportees">Mes ench�res remport�es</label>
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
						<input type="checkbox" id="ventesNonD�but�es"
							name="ventesNonD�but�es"> <label for="ventesNonD�but�es">Mes
							ventes non d�but�es</label>
					</p>
					<p>
						<input type="checkbox" id="ventesTerminees" name="ventesTerminees">
						<label for="ventesTerminees">Ventes termin�es</label>
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
							<p class="card-text">Fin de l'ench�re : ${a.dateFin }</p>
							<p class="card-text">Vendeur : <a href="${pageContext.request.contextPath}/profil?idUser=${a.utilisateur.id}">${a.utilisateur.pseudo }</a></p>
							<a href="${pageContext.request.contextPath}/detail"
								class="btn btn-primary">D�tail</a>
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