package fr.eni.javaee.eniencheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {

	private int id;
	private String libelle;
	
	private List<ArticleVendu> listeArticlesVendus;

	public Categorie() {
		this.listeArticlesVendus = new ArrayList<>();
	}

	public Categorie(int id, String libelle, List<ArticleVendu> listeArticlesVendus) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.listeArticlesVendus = listeArticlesVendus;
	}

	public Categorie(String libelle, List<ArticleVendu> listeArticlesVendus) {
		super();
		this.libelle = libelle;
		this.listeArticlesVendus = listeArticlesVendus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getListeArticlesVendus() {
		return listeArticlesVendus;
	}

	public void setListeArticlesVendus(List<ArticleVendu> listeArticlesVendus) {
		this.listeArticlesVendus = listeArticlesVendus;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", libelle=" + libelle + ", listeArticlesVendus=" + listeArticlesVendus + "]";
	}
	
}
