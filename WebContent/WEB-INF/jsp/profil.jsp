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

	<div class="block-profil">
		<p>
			<span class="span-profil">Pseudo :</span> ${pseudo}
		</p>
		<p>
			<span class="span-profil">Nom :</span> ${nom}
		</p>
		<p>
			<span class="span-profil">Prénom :</span> ${prenom}
		</p>
		<p>
			<span class="span-profil">Email :</span> ${email}
		</p>
		<p>
			<span class="span-profil">Téléphone :</span> ${telephone}
		</p>
		<p>
			<span class="span-profil">Rue :</span> ${rue}
		</p>
		<p>
			<span class="span-profil">Code postal :</span> ${cp}
		</p>
		<p>
			<span class="span-profil">Ville :</span> ${ville}
		</p>

		<c:choose>
			<c:when test="${id == idUser}">
				<a
					href="${pageContext.request.contextPath}/modifyProfil?idUser=${id}"
					class="btn btn-secondary btn-lg active" id="btn-profil-modify"
					role="button" aria-pressed="true">Modifier</a>
				<a href="${pageContext.request.contextPath}/encheres"
					class="btn btn-secondary btn-lg active" id="btn-profil-prev"
					role="button" aria-pressed="true">Retour</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/encheres"
					class="btn btn-secondary btn-lg active" id="btn-profil-prev"
					role="button" aria-pressed="true">Retour</a>
			</c:otherwise>
		</c:choose>


	</div>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>