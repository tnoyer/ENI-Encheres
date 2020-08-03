package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;

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
				|| art.getDateFin() == null
				|| art.getRetrait().getRue() == null || art.getRetrait().getRue().trim().isEmpty()
				|| art.getRetrait().getCodePostal() == null || art.getRetrait().getCodePostal().trim().isEmpty()
				|| art.getRetrait().getVille() == null || art.getRetrait().getVille().trim().isEmpty()) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_CHAMPS_OBLIGATOIRE);
		}
	}
}

