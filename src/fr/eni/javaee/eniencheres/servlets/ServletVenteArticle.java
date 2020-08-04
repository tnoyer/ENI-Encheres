package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import fr.eni.javaee.eniencheres.bll.CategorieManager;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Categorie;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletVenteArticle
 */
@WebServlet("/nouvelleVente")
public class ServletVenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVenteArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("id");
		if(session.getAttribute("id") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
		} else {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				Utilisateur utilisateur = utilisateurManager.selectionnerUtilisateurParId(idUser);
				request.setAttribute("rue", utilisateur.getRue());
				request.setAttribute("cp", utilisateur.getCodePostal());
				request.setAttribute("ville", utilisateur.getVille());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/venteArticle.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/venteArticle.jsp");
				rd.forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		int idCategorie = Integer.parseInt(request.getParameter("categorie"));
		int prix = Integer.parseInt(request.getParameter("prix"));
		LocalDate dateDebut=null;
		LocalDate dateFin=null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebut = LocalDate.parse(request.getParameter("dateDebut"),dtf);
			dateFin = LocalDate.parse(request.getParameter("dateFin"),dtf);
		} catch(DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_ERR);
		}
		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("id");
		
		if(listeCodesErreur.size()>0) {
			request.setAttribute("nomArticle", nomArticle);
			request.setAttribute("description", description);
			request.setAttribute("idCategorie", idCategorie);
			request.setAttribute("prix", prix);
			request.setAttribute("rue", rue);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/venteArticle.jsp");
			rd.forward(request, response);
		} else {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			CategorieManager categorieManager = new CategorieManager();
			ArticleManager articleManager = new ArticleManager();
			try {
				Utilisateur utilisateur = utilisateurManager.selectionnerUtilisateurParId(idUser);
				Categorie categorie = categorieManager.selectionnerCategorie(idCategorie);
				ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebut, dateFin, prix, prix, utilisateur, categorie);
				articleManager.insererArticle(article);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
				rd.forward(request, response);
			} catch (BusinessException e) {
				// je renvois les champs déjà remplis avant l'erreur
				request.setAttribute("nomArticle", nomArticle);
				request.setAttribute("description", description);
				request.setAttribute("idCategorie", idCategorie);
				request.setAttribute("prix", prix);
				request.setAttribute("rue", rue);
				request.setAttribute("cp", cp);
				request.setAttribute("ville", ville);
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/venteArticle.jsp");
				rd.forward(request, response);
			}
		}
	}
}
