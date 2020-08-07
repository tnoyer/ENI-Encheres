package fr.eni.javaee.eniencheres.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate date;
	private int montant;
	private int idUtilisateur;
	private int idArticle;
	
	public Enchere() {
		super();
	}

	public Enchere(LocalDate date, int montant, int idUtilisateur, int idArticle) {
		super();
		this.date = date;
		this.montant = montant;
		this.idUtilisateur = idUtilisateur;
		this.idArticle = idArticle;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	
}
