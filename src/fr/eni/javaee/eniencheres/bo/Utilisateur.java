package fr.eni.javaee.eniencheres.bo;

import java.util.List;

public class Utilisateur {
	
	//attributs
	private int id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private int admin;
	
	private List<ArticleVendu> listeArticlesVendus;
	private List<ArticleVendu> listeArticlesAchetes;
	
	//constructeurs
	public Utilisateur() {
		super();
	}

	public Utilisateur(int id, String pseudo, String rue, String codePostal, String ville) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Utilisateur(String pseudo, String rue, String codePostal, String ville) {
		super();
		this.pseudo = pseudo;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
	}


	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, int credit) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.credit = credit;
	}


	public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int admin) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.admin = admin;
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int admin) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.admin = admin;
	}

	public Utilisateur(int id, String pseudo, String motDePasse) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
	}

	public Utilisateur(int id, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int admin,
			List<ArticleVendu> listeArticlesVendus, List<ArticleVendu> listeArticlesAchetes) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.admin = admin;
		this.listeArticlesVendus = listeArticlesVendus;
		this.listeArticlesAchetes = listeArticlesAchetes;
	}

	//getters et setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public List<ArticleVendu> getListeArticlesVendus() {
		return listeArticlesVendus;
	}

	public void setListeArticlesVendus(List<ArticleVendu> listeArticlesVendus) {
		this.listeArticlesVendus = listeArticlesVendus;
	}

	public List<ArticleVendu> getListeArticlesAchetes() {
		return listeArticlesAchetes;
	}

	public void setListeArticlesAchetes(List<ArticleVendu> listeArticlesAchetes) {
		this.listeArticlesAchetes = listeArticlesAchetes;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville="
				+ ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", admin=" + admin
				+ ", listeArticlesVendus=" + listeArticlesVendus + ", listeArticlesAchetes=" + listeArticlesAchetes
				+ "]";
	}

	

}
