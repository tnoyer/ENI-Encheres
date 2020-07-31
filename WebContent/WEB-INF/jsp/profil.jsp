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
	
	<div class="block-profil">
		<p>
			<span class="span-profil">Pseudo :</span>
			${pseudo}
		</p>
		<p>
			<span class="span-profil">Nom :</span>
			${nom}
		</p>
		<p>
			<span class="span-profil">Prénom :</span>
			${prenom}
		</p>
		<p>
			<span class="span-profil">Email :</span>
			${email}
		</p>
		<p>
			<span class="span-profil">Téléphone :</span>
			${telephone}
		</p>
		<p>
			<span class="span-profil">Rue :</span>
			${rue}
		</p>
		<p>
			<span class="span-profil">Code postal :</span>
			${cp}
		</p>
		<p>
			<span class="span-profil">Ville :</span>
			${ville}
		</p>
		<a href="${pageContext.request.contextPath}/modifyProfil?idUser=${id}"
					class="btn btn-secondary btn-lg active" id="btn-profil-modify"
					role="button" aria-pressed="true">Modifier</a>
	</div>
	
	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>