package fr.eni.javaee.eniencheres.bo;

import java.time.LocalDateTime;

public class Enchere {

	private LocalDateTime date;
	private int montant;
	public ArticleVendu articleVendu;
	public Utilisateur utilisateur;
	
	public Enchere() {
		super();
	}
	
	public Enchere(LocalDateTime date, int montant) {
		super();
		this.date = date;
		this.montant = montant;
	}

	public Enchere(LocalDateTime date, int montant, ArticleVendu articleVendu, Utilisateur utilisateur) {
		super();
		this.date = date;
		this.montant = montant;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Enchere [date=" + date + ", montant=" + montant + ", articleVendu=" + articleVendu + ", utilisateur="
				+ utilisateur + "]";
	}
	
	
}
