package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletDeleteAccount
 */
@WebServlet("/deleteAccount")
public class ServletDeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeleteAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		try {
			utilisateurManager.supprimerUtilisateur(idUser);
			HttpSession session = request.getSession();
			session.removeAttribute("connected");
			session.removeAttribute("id");
			session.removeAttribute("pseudo");
			response.sendRedirect(request.getContextPath()+"/encheres");
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifyProfil.jsp");
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
