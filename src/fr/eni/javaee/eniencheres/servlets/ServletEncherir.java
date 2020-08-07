package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Enchere;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/encherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int prix = Integer.parseInt(request.getParameter("proposition"));
		int idArt = Integer.parseInt(request.getParameter("idArt"));
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("id");
		System.out.println("prix : "+prix);
		System.out.println("idArt : "+idArt);
		System.out.println("idUser : "+idUser);
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		ArticleManager articleManager = new ArticleManager();
		try {
			Utilisateur encherisseurPrecedent = null;
			Utilisateur utilisateur = utilisateurManager.selectionnerUtilisateurParId(idUser);
			ArticleVendu article = articleManager.selectionnerArticle(idArt);
			//infos sur la précedente enchère 
			Enchere encherePrecedente = articleManager.selectionnerDerniereEnchere(idArt);
			int montantEncherePrecedente = 0;
			if(encherePrecedente != null) {
				montantEncherePrecedente = encherePrecedente.getMontant();
				encherisseurPrecedent = utilisateurManager.selectionnerUtilisateurParId(encherePrecedente.getIdUtilisateur());
			}
			//précédente enchère de l'utilisateur connecté
			Enchere encherePrecedenteUtilisateurConnecte = articleManager.selectionnerDerniereEnchereUtilisateurConnecte(idArt, idUser);
			if(encherePrecedenteUtilisateurConnecte != null) {
				//traitement de la proposition d'une nouvelle enchère
				articleManager.modifierEnchere(idUser, idArt, prix, article.getPrixVente(), utilisateur.getCredit());
			} else {
				//traitement de la proposition d'une première enchère
				articleManager.insererEnchere(idUser, idArt, prix, article.getPrixVente(), utilisateur.getCredit());
			}
			//traitement du crédit du précédent encherisseur
			if(encherePrecedente != null) {
				int newCreditEncherisseurPrecedent = encherisseurPrecedent.getCredit() + montantEncherePrecedente;
				utilisateurManager.modifierCredit(encherisseurPrecedent.getId(), newCreditEncherisseurPrecedent);
			}
			//traitement du credit du nouvel encherisseur
			int newCredit = utilisateur.getCredit() - prix;
			utilisateurManager.modifierCredit(idUser, newCredit);
			
			response.sendRedirect(request.getContextPath() + "/encheres");
		} catch (BusinessException e) {
			e.printStackTrace();
			System.out.println(e.getListeCodesErreur());
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			response.sendRedirect(request.getContextPath() + "/detail?idArt="+idArt);
		}
	}

}
