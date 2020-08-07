package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/encheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListeEncheres() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		try {
			List<ArticleVendu> listeArticles = articleManager.selectionnerListeArticlesEnCours();
			System.out.println("liste : " + listeArticles);
			request.setAttribute("listeArticles", listeArticles);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur = new ArrayList<>();
		ArticleManager articleManager = new ArticleManager();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("connected") != null) {
			int idUser = (int) session.getAttribute("id");
			String articleFiltre = request.getParameter("articleFiltre");
			int idCat = Integer.parseInt(request.getParameter("categorie"));
			String radioChoice = request.getParameter("radioChoice");
			if(radioChoice.equals("achat")) {
				String encheresOuvertes = request.getParameter("encheresOuvertes");
				String encheresEnCours = request.getParameter("encheresEnCours");
				String encheresRemportees = request.getParameter("encheresRemportees");
				try {
					List<ArticleVendu> listeArticles = articleManager.selectionnerListeArticlesAchat(articleFiltre, idCat, encheresOuvertes, encheresEnCours, encheresRemportees, idUser);
					request.setAttribute("articleFiltre", articleFiltre);
					request.setAttribute("idCat", idCat);
					request.setAttribute("encheresOuvertes", encheresOuvertes);
					request.setAttribute("encheresEnCours", encheresEnCours);
					request.setAttribute("encheresRemportees", encheresRemportees);
					request.setAttribute("listeArticles", listeArticles);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
					rd.forward(request, response);
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
					rd.forward(request, response);
				}
			} else if(radioChoice.equals("vente")) {
				String ventesEnCours = request.getParameter("ventesEnCours");
				String ventesNonDebutees = request.getParameter("ventesNonDebutees");
				String ventesTerminees = request.getParameter("ventesTerminees");
				try {
					List<ArticleVendu> listeArticles = articleManager.selectionnerListeArticlesVente(articleFiltre, idCat, ventesEnCours, ventesNonDebutees, ventesTerminees, idUser);
					request.setAttribute("articleFiltre", articleFiltre);
					request.setAttribute("idCat", idCat);
					request.setAttribute("ventesEnCours", ventesEnCours);
					request.setAttribute("ventesNonDebutees", ventesNonDebutees);
					request.setAttribute("ventesTerminees", ventesTerminees);
					request.setAttribute("listeArticles", listeArticles);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
					rd.forward(request, response);
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
					rd.forward(request, response);
				}
			}
		} else {
			String articleFiltre = request.getParameter("articleFiltre");
			int idCat = Integer.parseInt(request.getParameter("categorie"));

			if (articleFiltre == null && idCat == 0) {
				listeCodesErreur.add(CodesResultatServlets.CHAMPS_VIDES);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				response.sendRedirect(request.getContextPath()+"/encheres");
			} else {
				try {
					List<ArticleVendu> listeArticles = articleManager.selectionnerlisteArticlesParNomEtCategorie(articleFiltre,
							idCat);
					System.out.println("liste : " + listeArticles);
					request.setAttribute("articleFiltre", articleFiltre);
					request.setAttribute("idCat", idCat);
					request.setAttribute("listeArticles", listeArticles);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
					rd.forward(request, response);
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
					rd.forward(request, response);
				}
			}
		}
		

	}

}
