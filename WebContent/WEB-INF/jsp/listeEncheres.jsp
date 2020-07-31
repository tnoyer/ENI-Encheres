<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<c:choose>
		<c:when test="${connected}">
			<jsp:include page="/WEB-INF/fragments/navConnected.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/fragments/navNotConnected.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>

	<h1 style="text-align: center; padding: 10px;">Liste des enchères</h1>

	<div class="block-content">
		<h2>Filtres :</h2>
		<%
		String articleFiltre="";
		if(request.getParameter("articleFiltre")!=null)
		{
			articleFiltre=request.getParameter("articleFiltre");
		}
		%>

		<form action="${pageContext.request.contextPath}/encheres"
			method="post" class="block-filter-art">
			<div class="block-art-cat">
				<p><input class="input-filter-art" type="text" name="articleFiltre" value="${articleFiltre}" placeholder="Nom de l'article..."/></p>
				<p class="p-cat"><label class="label-categorie" for="categorie">Catégories: </label>
				<select name="categorie" id="categorie">
					<option value="">Toutes</option>
					<option value="informatique">Informatique</option>
					<option value="ameublement">Ameublement</option>
					<option value="vetement">Vêtement</option>
					<option value="sport">Sport et loisirs</option>
				</select></p>
			</div>
			<div>
				<input class="btn-submit-filter-art" type="submit" value="Rechercher" />
			</div>
		</form>

<%-- 
		<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
		<p style="color: red;">Erreur :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
		<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>

		<table align="center">
			<thead>
				<tr>
					<td>Date</td>
					<td>Heure</td>
					<td>Action</td>
				</tr>
			</thead>
			<%
					List<Repas> listeRepas = (List<Repas>) request.getAttribute("listeRepas");
					if(listeRepas!=null && listeRepas.size()>0)
					{
				%>
			<tbody>
				<%
							for(Repas repas : listeRepas)
							{
							%>
				<tr>
					<td><%=repas.getDate()%></td>
					<td><%=repas.getHeure()%></td>

					<td><a
						href="<%=request.getContextPath()%>/repas?detail=<%=repas.getIdentifiant()%>&<%=dateFiltre%>">détail</a></td>
				</tr>
				<%
								if(String.valueOf(repas.getIdentifiant()).equals(request.getParameter("detail")))
								{
							%>
				<tr>
					<td colspan="3">
						<ul>
							<%
												for(Aliment aliment:repas.getListeAliments())
												{
												%>
							<li><%=aliment.getNom()%></li>
							<%
												}
												%>
						</ul>
					</td>
				</tr>
				<%
								}
							}
							%>
			</tbody>
			<%
					}
					else
					{
				%>
			<p>Il n'y a aucun repas à afficher
			<P>
				<%
					}
				%>
			
		</table>
--%>

	</div>


	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>