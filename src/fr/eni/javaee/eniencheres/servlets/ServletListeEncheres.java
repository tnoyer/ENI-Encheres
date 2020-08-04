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
			List<ArticleVendu> listeArticles = articleManager.selectionnerlisteArticles();
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
		String articleFiltre = request.getParameter("articleFiltre");
		int idCat = Integer.parseInt(request.getParameter("categorie"));

		if (articleFiltre == null || idCat == 0) {
			listeCodesErreur.add(CodesResultatServlets.CHAMPS_VIDES);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
			rd.forward(request, response);
		} else {
			ArticleManager articleManager = new ArticleManager();
			try {
				List<ArticleVendu> listeArticles = articleManager.selectionnerlisteArticlesParNomEtId(articleFiltre,
						idCat);
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

	}

}
