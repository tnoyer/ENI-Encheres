package fr.eni.javaee.eniencheres.bo;

import java.time.LocalDate;

public class ArticleVendu {
	
	//attributs
	private int id;
	private String nom;
	private String description;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	public Utilisateur utilisateur;
	public Categorie categorie;
	public Retrait retrait;
	public String imagePath;
	
	//constructeurs
	public ArticleVendu() {
		super();
	}

	public ArticleVendu(int id, String nom, String description, LocalDate dateDebut, LocalDate dateFin,
			int prixInitial, int prixVente, String etatVente) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}

	public ArticleVendu(int id, String nom, String description, LocalDate dateDebut, LocalDate dateFin,
			int prixInitial, int prixVente, String etatVente, Utilisateur utilisateur, Categorie categorie,
			Retrait retrait, String imagePath) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.imagePath = imagePath;
	}

	public ArticleVendu(String nom, String description, LocalDate dateDebut, LocalDate dateFin, int prixInitial,
			int prixVente, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}


	public ArticleVendu(String nom, String description, LocalDate dateDebut, LocalDate dateFin, int prixInitial,
			int prixVente) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "ArticleVendu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", prixInitial=" + prixInitial + ", prixVente=" + prixVente + ", etatVente="
				+ etatVente + ", utilisateur=" + utilisateur + ", categorie=" + categorie + ", retrait=" + retrait
				+ ", imagePath=" + imagePath + "]";
	}

	
}
