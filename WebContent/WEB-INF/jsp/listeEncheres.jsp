<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>

	<c:choose>
		<c:when test="${connected}">
			<jsp:include page="/WEB-INF/fragments/listeEncheresConnected.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/fragments/listeEncheresNotConnected.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>