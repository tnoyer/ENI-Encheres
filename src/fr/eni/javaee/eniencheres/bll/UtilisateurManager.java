package fr.eni.javaee.eniencheres.bll;

import fr.eni.javaee.eniencheres.dal.DAOFactory;
import fr.eni.javaee.eniencheres.dal.UtilisateurDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur selectionnerUtilisateurParLoginEtPassword(String login, String password)
			throws BusinessException {
		return this.utilisateurDAO.selectByLoginAndPassword(login, password);
	}
	
	public Utilisateur selectionnerUtilisateurParId(int id) throws BusinessException{
		return this.utilisateurDAO.selectById(id);
	}

	public void insererUtilisateur(Utilisateur utilisateur, String confirm) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerPseudo(utilisateur.getPseudo(), businessException);
		this.validerMotDePasse(utilisateur.getMotDePasse(), confirm, businessException);
		this.validerChamps(utilisateur, confirm, businessException);
		this.validerEmail(utilisateur.getEmail(), businessException);
		this.validerUnicitePseudo(utilisateur.getPseudo(), businessException);
		this.validerUniciteMail(utilisateur.getEmail(), businessException);
		
		if (!businessException.hasErreurs()) {
			this.utilisateurDAO.insertUtilisateur(utilisateur);
		} else {
			throw businessException;
		}
	}
	
	private void validerUniciteMail(String email, BusinessException businessException) throws BusinessException {
		if(!utilisateurDAO.isUniqueMail(email)) {
			businessException.ajouterErreur(CodesResultatBLL.UNIQUE_MAIL_ERR);
		}
	}

	private void validerUnicitePseudo(String pseudo, BusinessException businessException) throws BusinessException {
		if(!utilisateurDAO.isUniquePseudo(pseudo)) {
			businessException.ajouterErreur(CodesResultatBLL.UNIQUE_PSEUDO_ERR);
		}
		
	}

	public void supprimerUtilisateur(int id) throws BusinessException{
		this.utilisateurDAO.deleteUtilisateur(id);
	}
	
	public void modifierUtilisateur(Utilisateur utilisateur, String confirm) throws BusinessException{
		BusinessException businessException = new BusinessException();
		this.validerPseudo(utilisateur.getPseudo(), businessException);
		this.validerMotDePasse(utilisateur.getMotDePasse(), confirm, businessException);
		this.validerChamps(utilisateur, confirm, businessException);
		this.validerEmail(utilisateur.getEmail(), businessException);
		
		if (!businessException.hasErreurs()) {
			this.utilisateurDAO.modifyUtilisateur(utilisateur);;
		} else {
			throw businessException;
		}
	}

	private void validerEmail(String email, BusinessException businessException) {
		System.out.println("email : " + email);
		String regex = "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		System.out.println(m.matches());
		if (!m.matches()) {
			businessException.ajouterErreur(CodesResultatBLL.EMAIL_ERR);
		}
	}

	private void validerChamps(Utilisateur utilisateur, String confirm, BusinessException businessException) {
		if (utilisateur.getPseudo() == null || utilisateur.getPseudo().trim().isEmpty() || utilisateur.getNom() == null
				|| utilisateur.getNom().trim().isEmpty() || utilisateur.getPrenom() == null
				|| utilisateur.getPrenom().trim().isEmpty() || utilisateur.getEmail() == null
				|| utilisateur.getEmail().trim().isEmpty() || utilisateur.getRue() == null
				|| utilisateur.getRue().trim().isEmpty() || utilisateur.getCodePostal() == null
				|| utilisateur.getCodePostal().trim().isEmpty() || utilisateur.getVille() == null
				|| utilisateur.getVille().trim().isEmpty() || utilisateur.getMotDePasse() == null
				|| utilisateur.getMotDePasse().trim().isEmpty() || confirm == null || confirm.trim().isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_CHAMPS_OBLIGATOIRE);
		}

	}

	private void validerMotDePasse(String mdp, String confirm, BusinessException businessException) {
		if (!mdp.trim().equals(confirm.trim())) {
			businessException.ajouterErreur(CodesResultatBLL.PASSWORD_ERR);
		}

	}

	private void validerPseudo(String pseudo, BusinessException businessException) {
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		Matcher m = p.matcher(pseudo);
		while (m.find()) {
			if (!m.matches()) {
				businessException.ajouterErreur(CodesResultatBLL.PSEUDO_FORMAT_ERR);
				break;
			}
		}
	}
}
