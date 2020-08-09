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
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/encheres">ENI-Enchères</a>
		</div>
	</nav>

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

	<div class="block-btn-enchere">
		<a
			href="${pageContext.request.contextPath}/detail?idArt=${idArticle}"
			class="btn btn-secondary btn-lg active" id="btn-delete-profil"
			role="button" aria-pressed="true">Revenir au détail de l'article</a> 
		<a
			href="${pageContext.request.contextPath}/encheres"
			class="btn btn-secondary btn-lg active" id="btn-delete-profil"
			role="button" aria-pressed="true">Accueil</a>
	</div>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>