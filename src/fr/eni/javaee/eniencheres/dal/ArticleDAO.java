package fr.eni.javaee.eniencheres.dal;

import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;

public interface ArticleDAO {
	public void insertArticle(ArticleVendu art) throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public List<ArticleVendu> selectByNomAndCategorie(String nom, int id) throws BusinessException;
}
