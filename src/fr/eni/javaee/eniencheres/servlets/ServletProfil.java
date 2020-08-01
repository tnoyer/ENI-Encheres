package fr.eni.javaee.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.UtilisateurManager;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/profil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		System.out.println("idUser : "+idUser);
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		try {
			Utilisateur utilisateur = utilisateurManager.selectionnerUtilisateurParId(idUser);
			request.setAttribute("pseudo", utilisateur.getPseudo());
			request.setAttribute("nom", utilisateur.getNom());
			request.setAttribute("prenom", utilisateur.getPrenom());
			request.setAttribute("email", utilisateur.getEmail());
			request.setAttribute("telephone", utilisateur.getTelephone());
			request.setAttribute("rue", utilisateur.getRue());
			request.setAttribute("cp", utilisateur.getCodePostal());
			request.setAttribute("ville", utilisateur.getVille());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEncheres.jsp");
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
