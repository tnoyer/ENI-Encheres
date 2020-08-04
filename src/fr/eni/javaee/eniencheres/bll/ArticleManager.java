package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bo.ArticleVendu;
import fr.eni.javaee.eniencheres.dal.ArticleDAO;
import fr.eni.javaee.eniencheres.dal.DAOFactory;

public class ArticleManager {

private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public void insererArticle(ArticleVendu art) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerChamps(art, businessException);
		this.validerDate(art.getDateDebut(), art.getDateFin(), businessException);
		
		if (!businessException.hasErreurs()) {
			this.articleDAO.insertArticle(art);
		} else {
			throw businessException;
		}
	}
	
	public List<ArticleVendu> selectionnerlisteArticles() throws BusinessException{
		return this.articleDAO.selectAll();
	}
	
	public List<ArticleVendu> selectionnerlisteArticlesParNomEtId(String nom, int id) throws BusinessException{
		return this.articleDAO.selectByNomAndCategorie(nom, id);
	}
	
	public ArticleVendu selectionnerArticle(int id) throws BusinessException{
		return null;
		
	}

	private void validerDate(LocalDate dateDebut, LocalDate dateFin, BusinessException businessException) {
		if(dateDebut.compareTo(dateFin) > 0) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_ERR);
		}
	}

	private void validerChamps(ArticleVendu art, BusinessException businessException) {
		if (art.getNom() == null || art.getNom().trim().isEmpty()
				|| art.getDescription() == null || art.getDescription().trim().isEmpty()
				|| art.getCategorie().getId() == 0
				|| art.getPrixInitial() == 0 
				|| art.getPrixVente() == 0
				|| art.getDateDebut() == null
				|| art.getDateFin() == null) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_CHAMPS_OBLIGATOIRE);
		}
	}
}

