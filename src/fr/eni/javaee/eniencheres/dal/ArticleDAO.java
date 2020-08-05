package fr.eni.javaee.eniencheres.dal;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;

public interface ArticleDAO {
	public void insertArticle(ArticleVendu art) throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public List<ArticleVendu> selectByNomAndCategorie(String nom, int idCat) throws BusinessException;
	public List<ArticleVendu> selectListeAchat(String nom, int idCat, String encheresOuvertes, String encheresEnCours, String encheresRemportees, int idUser) throws BusinessException;
	public List<ArticleVendu> selectListeVente(String nom, int idCat, String ventesEnCours, String ventesNonDebutees, String ventesTerminees, int idUser) throws BusinessException;
	public ArticleVendu selectById(int id) throws BusinessException;
}
