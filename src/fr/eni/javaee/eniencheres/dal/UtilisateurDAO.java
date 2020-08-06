package fr.eni.javaee.eniencheres.dal;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur selectByLoginAndPassword(String login, String password) throws BusinessException;
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur selectById(int id) throws BusinessException;
	public void deleteUtilisateur(int id) throws BusinessException;
	public void modifyUtilisateur(Utilisateur utilisateur) throws BusinessException;
	public boolean isUniquePseudo(String pseudo) throws BusinessException;
	public boolean isUniqueMail(String email) throws BusinessException;
	public void modifyCredit(int id, int credit) throws BusinessException; 
}
