package fr.eni.javaee.eniencheres.dal;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;

public interface ArticleDAO {
	public void insertArticle(ArticleVendu art) throws BusinessException;
	
}
