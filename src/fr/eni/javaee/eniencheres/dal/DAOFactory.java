package fr.eni.javaee.eniencheres.dal;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
}
