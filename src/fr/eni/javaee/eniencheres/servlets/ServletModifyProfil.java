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
import fr.eni.javaee.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifyProfil
 */
@WebServlet("/modifyProfil")
public class ServletModifyProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletModifyProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getParameter("idUser"));

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
			request.setAttribute("credit", utilisateur.getCredit());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifyProfil.jsp");
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

		int id = Integer.parseInt(request.getParameter("idUser"));
		System.out.println("id modify : " + id);
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("password");
		String confirm = request.getParameter("confirm");

		// Je modifie l'utilisateur
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		try {
			Utilisateur utilisateur = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp);
			utilisateurManager.modifierUtilisateur(utilisateur, confirm);
			HttpSession session = request.getSession();
			session.removeAttribute("pseudo");
			session.setAttribute("pseudo", utilisateur.getPseudo());
			response.sendRedirect(request.getContextPath() + "/encheres");
		} catch (BusinessException e) {
			// je renvois les champs déjà remplis avant l'erreur
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			// Sinon je retourne à la page d'inscription pour indiquer les problèmes:
			e.printStackTrace();
			System.out.println(e.getListeCodesErreur());
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			rd.forward(request, response);
		}
	}

}
