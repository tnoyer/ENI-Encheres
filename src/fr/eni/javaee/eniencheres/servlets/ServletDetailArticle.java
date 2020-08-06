package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Enchere;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/detail")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetailArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		int idArticle = Integer.parseInt(request.getParameter("idArt"));
		System.out.println("idArt : "+idArticle);
		try {
			ArticleVendu art = articleManager.selectionnerArticle(idArticle);
			Enchere enchere = articleManager.selectionnerEnchere(idArticle);
			if(enchere != null) {
				Utilisateur utilisateur = utilisateurManager.selectionnerUtilisateurParId(enchere.getIdUtilisateur());
				request.setAttribute("utilisateur", utilisateur);
			}
			System.out.println(art);
			request.setAttribute("art", art);
			request.setAttribute("enchere", enchere);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailArticle.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			response.sendRedirect(request.getContextPath() + "/encheres");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
