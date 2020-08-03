package fr.eni.javaee.eniencheres.bo;

import java.util.List;

public class Retrait {

	//attributs
	private String rue;
	private String codePostal;
	private String ville;
	
	private List<ArticleVendu> listeArticlesVendus;
	
	//constructeurs
	public Retrait() {
		super();
	}
	

	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	

	public String getCodePostal() {
		return codePostal;
	}

	//getters et setters
	public String getRue() {
		return rue;
	}

	public String getVille() {
		return ville;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setRue(String rue) {
		this.rue = rue;
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

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	
}
