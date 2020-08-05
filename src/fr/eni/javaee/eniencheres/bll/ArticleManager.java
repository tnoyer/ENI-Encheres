package fr.eni.javaee.eniencheres.bll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	public List<ArticleVendu> selectionnerlisteArticlesParNomEtCategorie(String nom, int idCat) throws BusinessException{
		return this.articleDAO.selectByNomAndCategorie(nom, idCat);
	}
	
	public List<ArticleVendu> selectionnerListeArticlesAchat(String nom, int idCat, String encheresOuvertes, String encheresEnCours, String encheresRemportees, int idUser) throws BusinessException{
		return this.articleDAO.selectListeAchat(nom, idCat, encheresOuvertes, encheresEnCours, encheresRemportees, idUser);
	}
	
	public List<ArticleVendu> selectionnerListeArticlesVente(String nom, int idCat, String ventesEnCours, String ventesNonDebutees, String ventesTerminees, int idUser) throws BusinessException{
		return this.articleDAO.selectListeVente(nom, idCat, ventesEnCours, ventesNonDebutees, ventesTerminees, idUser);
	}
	
	public ArticleVendu selectionnerArticle(int id) throws BusinessException{
		return this.articleDAO.selectById(id);
		
	}

	private void validerDate(LocalDate dateDebut, LocalDate dateFin, BusinessException businessException) {
		if(dateDebut.compareTo(dateFin) > 0) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_ERR);
		}
		LocalDate dateJour = LocalDate.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String text = dateJour.format(formatters);
	    dateJour = LocalDate.parse(text, formatters);
		if(dateDebut.compareTo(dateJour) < 0) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_DEBUT_ERR);
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

