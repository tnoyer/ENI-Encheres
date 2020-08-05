package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;
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
		int idArticle = Integer.parseInt(request.getParameter("idArt"));
		try {
			ArticleVendu art = articleManager.selectionnerArticle(idArticle);
			System.out.println(art);
			request.setAttribute("art", art);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailArticle.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailArticle.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
