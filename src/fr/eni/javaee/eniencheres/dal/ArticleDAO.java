package fr.eni.javaee.eniencheres.dal;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.bo.Enchere;

public interface ArticleDAO {
	public void insertArticle(ArticleVendu art) throws BusinessException;
	public List<ArticleVendu> selectEnCours() throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public List<ArticleVendu> selectByNomAndCategorie(String nom, int idCat) throws BusinessException;
	public List<ArticleVendu> selectListeAchat(String nom, int idCat, String encheresOuvertes, String encheresEnCours, String encheresRemportees, int idUser) throws BusinessException;
	public List<ArticleVendu> selectListeVente(String nom, int idCat, String ventesEnCours, String ventesNonDebutees, String ventesTerminees, int idUser) throws BusinessException;
	public ArticleVendu selectById(int id) throws BusinessException;
	public void insertEnchere(int no_utilisateur, int no_article, int montant_enchere) throws BusinessException;
	public Enchere selectEnchere(int idArt) throws BusinessException; 
	public Enchere selectDerniereEnchere(int idArt) throws BusinessException;
	public void modifyEnchere(int no_utilisateur, int no_article, int montant_enchere) throws BusinessException;
	public int maxEnchere(int no_utilisateur, int no_article) throws BusinessException;
	public Enchere selectDerniereEnchereUtilisateurConnecte(int idArt, int idUser) throws BusinessException;
}
