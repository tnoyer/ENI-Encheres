<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<nav class="navbar navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand" href="#">ENI-Enchères</a>
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

	<div class="block-modify-profil">
		<form method="post" action="${pageContext.request.contextPath}/modifyProfil">
			<p>
				<label class="label-modify-profil" for="pseudo">Pseudo*: </label> 
				<input class="input-modify-profil" type="text" name="pseudo" id="pseudo" value="${pseudo}"> 
				<label class="label-modify-profil" for="nom">Nom*: </label> 
				<input type="text" name="nom" id="nom" value=${nom}>
			</p>
			<p>
				<label class="label-modify-profil" for="prenom">Prénom*: </label> 
				<input class="input-inscription" type="text" name="prenom" id="prenom" value="${prenom}">
				<label class="label-modify-profil" for="email">Email*: </label> 
				<input type="email" name="email" id="email" value="${email}">
			</p>
			<p>
				<label class="label-modify-profil" for="telephone">Téléphone:</label> 
				<input class="input-inscription" type="text" name="telephone" id="telephone" value="${telephone}"> 
				<label class="label-modify-profil" for="rue">Rue*: </label> 
				<input type="text" name="rue" id="rue" value="${rue}">
			</p>
			<p>
				<label class="label-modify-profil" for="cp">Code postal*: </label> 
				<input class="input-inscription" type="text" name="cp" id="cp" value="${cp}"> 
				<label class="label-modify-profil" for="ville">Ville*: </label> 
				<input type="text" name="ville" id="ville" value="${ville }">
			</p>
			<p>
				<label class="label-modify-profil" for="actualPassword">Mot de passe actuel*: </label> 
				<input class="input-inscription" type="password" name="actualPassword" id="actualPassword"> 
			</p>
			<p>
				<label class="label-modify-profil" for="newPassword">Nouveau mot de passe*: </label> 
				<input class="input-inscription" type="password" name="newPassword" id="newPassword"> 
				<label class="label-modify-profil" for="confirm">Confirmation*: </label> 
				<input type="password" name="confirm" id="confirm">
			</p>
			<p>Crédit : ${credit}</p>
			<div class="block-btn-modify-profil">
				<input class="btn-modify-profil" type="submit" value="Enregistrer">
				<a href="${pageContext.request.contextPath}/deleteAccount?idUser=${id}"
					class="btn btn-secondary btn-lg active" id="btn-delete-profil"
					role="button" aria-pressed="true">Supprimer mon compte</a>
			</div>
		</form>
	</div>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>