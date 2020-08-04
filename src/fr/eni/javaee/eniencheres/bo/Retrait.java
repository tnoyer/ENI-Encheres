package fr.eni.javaee.eniencheres.bo;

import java.util.List;

public class Retrait {

	//attributs
	private int idUtilisateur;
	private String rue;
	private String codePostal;
	private String ville;
	
	private List<ArticleVendu> listeArticlesVendus;
	
	//constructeurs
	public Retrait() {
		super();
	}

	public Retrait(int idUtilisateur, String rue, String codePostal, String ville) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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

	public List<ArticleVendu> getListeArticlesVendus() {
		return listeArticlesVendus;
	}

	public void setListeArticlesVendus(List<ArticleVendu> listeArticlesVendus) {
		this.listeArticlesVendus = listeArticlesVendus;
	}
	
	
}
